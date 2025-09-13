package com.kosowski.firstSpringBootApp.rest;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class firstController {

    @Value("${my.name}")
    private String name;

    @GetMapping("/hello" )
    public String hello() {
        log.info("Hello endpoint was called");
        return "Hello World! My name is " + name;
    }
}
