package com.studentregistration.student_registration.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.studentregistration.student_registration.models.Course;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin
public class CourseController {
    
    private List<Course> courses = Arrays.asList(
                                    new Course(1, "Java"),
                                    new Course(2, "Python"),
                                    new Course(3, "C#"));

    @GetMapping("courses")
    public List<Course> getCourses() {
        return courses;
    }
    
}
