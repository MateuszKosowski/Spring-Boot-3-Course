package com.kosowski.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class DemoController {

    @GetMapping("/")
    public String sayHello(Model theModel) {
        theModel.addAttribute("theDate", LocalDateTime.now());
        return "hello";
    }

    @GetMapping("/form")
    public String showForm(Model theModel) {
        return "form";
    }

    @PostMapping("/form-response")
    public String formResponse(Model theModel, @RequestParam("name") String name) {
        theModel.addAttribute("name_model", name);
        return "form-response";
    }
}
