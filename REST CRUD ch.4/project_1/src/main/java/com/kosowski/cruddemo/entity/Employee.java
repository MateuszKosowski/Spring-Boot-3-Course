package com.kosowski.cruddemo.entity;

import jakarta.persistence.*;
import lombok.Data;

// During spring boot app staring Hibernate (ORM engine) will find class with @Entity annotation
// Based on annotations like @Column, @Id, @GeneratedValue Hibernate will create mapping between database table and class fields
@Entity
@Table(name = "employee")
// @Data annotation will generate getters, setters and toString method for all fields in the class, so we don't have to write them manually
@Data
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
}
