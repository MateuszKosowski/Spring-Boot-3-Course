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

    // When in some controller method an exception is thrown, DispatcherServlet will handle it.
    // Then it uses ExceptionHandlerExceptionResolver to find a method annotated with @ExceptionHandler
    // method is chosen based on method parameter type or exception class for example:
    // @ExceptionHandler(StudentNotFoundException.class)
    // If such method is found, it will be invoked
    // and the response from that method will be returned to the client.
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {
        // Response Entity - Spring class - is used to customize the response body and status code for the HTTP response
        StudentErrorResponse errorBody = new StudentErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
        StudentErrorResponse errorBody = new StudentErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
