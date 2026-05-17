package com.kosowski.validationapp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Customer {
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min=4, message = "Last name must be at least 4 characters long")
    private String lastName;
}
