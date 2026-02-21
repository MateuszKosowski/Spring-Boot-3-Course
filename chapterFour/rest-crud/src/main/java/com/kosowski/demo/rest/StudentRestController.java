package com.kosowski.demo.rest;

import com.kosowski.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return students.stream()
                       .filter(student -> student.firstName().equalsIgnoreCase(name))
                       .collect(Collectors.toList());
    }
}
