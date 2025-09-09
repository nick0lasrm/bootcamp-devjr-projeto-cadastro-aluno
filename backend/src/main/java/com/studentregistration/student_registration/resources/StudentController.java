package com.studentregistration.student_registration.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.studentregistration.student_registration.models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Nickolas", "nickolas@gmail.com", "(11) 11111-1111", 1, true, false, false),
            new Student(2, "Raphael", "raphael@gmail.com", "(22) 22222-2222", 2, false, true, false),
            new Student(3, "Machado", "machado@gmail.com", "(33) 33333-3333", 3, false, false, true)));

    @PostMapping("students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        student.setId(students.size() + 1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        return ResponseEntity.created(location).body(student);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {

        Student std = students.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!"));

        return ResponseEntity.ok(std);
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

}
