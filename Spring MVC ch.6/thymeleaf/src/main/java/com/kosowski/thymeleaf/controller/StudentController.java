package com.kosowski.thymeleaf.controller;

import com.kosowski.thymeleaf.model.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
@Log4j2
public class StudentController {

    @Value("${list.of.countries}")
    private List<String> listOfCountries;

    @GetMapping("/")
    public String showForm(Model theModel){
        theModel.addAttribute("student", new Student());
        theModel.addAttribute("listOfCountries", listOfCountries);
        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String process(@ModelAttribute("student") Student theStudent){

        log.info("Student from the form: {}", theStudent);

        return "student-confirmation";
    }
}
