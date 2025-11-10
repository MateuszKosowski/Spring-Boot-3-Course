package com.kosowski.cruddemo.dao;

import com.kosowski.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Student> findAll(String firstLetter){
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.lastName LIKE :firstLetter ORDER BY s.lastName",
                Student.class);
        query.setParameter("firstLetter", firstLetter + "%");
        return query.getResultList();
    }
}
