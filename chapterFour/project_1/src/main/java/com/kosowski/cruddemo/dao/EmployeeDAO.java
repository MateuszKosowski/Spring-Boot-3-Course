package com.kosowski.cruddemo.dao;

import com.kosowski.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
