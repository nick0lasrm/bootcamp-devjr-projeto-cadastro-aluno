//Masks
$("#inputPhone").mask("(99) 99999-9999");

var students = []
var courses = [];

//OnLoad
loadStudents();
loadCourses();

function loadCourses() {

    $.ajax({
        url: "http://localhost:8080/courses",
        type: "GET",
        async: false,
        success: (response) => {
            courses = response;
            for (var crs of courses) {
                document.getElementById("selectCourse").innerHTML += `<option value="${crs.id}">${crs.name}</option>`
            }
        }
    });

}

//Load all students
function loadStudents() {

    $.getJSON("http://localhost:8080/students", (response) => {
        students = response;
        for (let std of students) {
            addNewRow(std);
        }
    });

}

//save a student
function save() {

    var std = {
        id: students.length + 1,
        name: document.getElementById("inputName").value,
        email: document.getElementById("inputEmail").value,
        phone: document.getElementById("inputPhone").value,
        idCourse: document.getElementById("selectCourse").value,
        morning: document.getElementById("radioMorning").checked,
        afternoon: document.getElementById("radioAfternoon").checked,
        evening: document.getElementById("radioEvening").checked,
    };

    $.ajax({
        url: "http://localhost:8080/students",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(std),
        success: (student) => {
            addNewRow(student);
            students.push(student);
            document.getElementById("formStudent").reset();
        }
    });

}

//Add new Row
function addNewRow(std) {
    var table = document.getElementById("studentsTable");

    var newRow = table.insertRow();

    //Insert student id
    var idNode = document.createTextNode(std.id);
    newRow.insertCell().appendChild(idNode);

    //Insert student name
    var nameNode = document.createTextNode(std.name);
    newRow.insertCell().appendChild(nameNode);

    //Insert student email
    var emailNode = document.createTextNode(std.email);
    var cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.appendChild(emailNode);

    var phoneNode = document.createTextNode(std.phone);
    newRow.insertCell().appendChild(phoneNode);

    //Insert student course
    var courseNode = document.createTextNode(courses[std.idCourse - 1].name);
    newRow.insertCell().appendChild(courseNode);

    //Insert courses options
    var options = "";
    if (std.morning) {
        options = "<span>Manhã</span>";
    }

    if (std.afternoon) {
        options += "<span>Tarde</span>";
    }

    if (std.evening) {
        options += "<span>Noite</span>";
    }

    cell = newRow.insertCell();
    cell.className = "d-none d-md-table-cell";
    cell.innerHTML = options;

}












