package com.kosowski.securitydemo.repository;

import com.kosowski.securitydemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
