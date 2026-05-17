package com.kosowski.thymeleaf.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String favLang;
    private List<String> favSystems;
}
