package com.studentregistration.student_registration.resources;

import java.net.URI;
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
    
    private List<Student> students = Arrays.asList(
                                new Student(1, "Nickolas", "nickolas@gmail.com", "(99) 99999-9999", 1, true, false, false),
                                new Student(2, "Raphael", "raphael@gmail.com", "(99) 99999-9999", 2, true, false, false),
                                new Student(3, "Machado", "machado@gmail.com", "(99) 99999-9999", 3, true, false, false));

    @PostMapping("students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        student.setId(students.size()+1);
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
