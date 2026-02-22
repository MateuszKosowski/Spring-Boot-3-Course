package com.kosowski.demo.rest;

import com.kosowski.demo.entity.StudentErrorResponse;
import com.kosowski.demo.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// It is a central place to handle exceptions instead of in each controller
@ControllerAdvice
public class StudentRestExceptionHandler {


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
