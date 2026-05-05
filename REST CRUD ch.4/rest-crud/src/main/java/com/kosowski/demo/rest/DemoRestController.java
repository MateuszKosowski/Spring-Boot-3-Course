package com.kosowski.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
