package com.kosowski.cruddemo.dao;

import com.kosowski.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);
    Student findById(int id);
    List<Student> findAll(String fistLetter);
    void update(Student student);
    void delete(int id);
}
