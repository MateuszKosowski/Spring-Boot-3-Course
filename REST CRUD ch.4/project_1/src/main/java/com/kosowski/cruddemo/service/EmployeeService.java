package com.kosowski.cruddemo.service;

import com.kosowski.cruddemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
}
