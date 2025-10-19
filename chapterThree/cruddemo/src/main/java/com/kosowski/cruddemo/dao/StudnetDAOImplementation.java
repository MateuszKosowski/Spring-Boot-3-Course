package com.kosowski.cruddemo.dao;

import com.kosowski.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudnetDAOImplementation implements StudentDAO {

    // Spring boot automatically create bean of this type
    private EntityManager entityManager;

    // @Autowired means that Sping will use this constructor to create an instance of this class
    @Autowired
    public StudnetDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Student student){
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id){
        return entityManager.find(Student.class, id);
    }
}
