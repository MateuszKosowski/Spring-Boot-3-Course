package com.kosowski.demo.rest;

import com.kosowski.demo.entity.Student;
import com.kosowski.demo.entity.StudentErrorResponse;
import com.kosowski.demo.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Smith"));
        students.add(new Student("Jane", "Williams"));
        students.add(new Student("Bob", "Jones"));
    }

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{name}")
    public List<Student> getStudentByName(@PathVariable String name) {

        if(name.equals("error")) {
            throw new RuntimeException("Error occurred");
        }

        List<Student> selectedStuednts = students.stream()
                                           .filter(student -> student.firstName().equalsIgnoreCase(name))
                                           .collect(Collectors.toList());

        if (selectedStuednts.isEmpty()) {
            throw new StudentNotFoundException("Student with name " + name + " not found");
        }

        return selectedStuednts;
    }
}
