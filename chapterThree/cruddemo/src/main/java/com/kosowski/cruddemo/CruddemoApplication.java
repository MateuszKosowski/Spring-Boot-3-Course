package com.kosowski.cruddemo;

import com.kosowski.cruddemo.dao.StudentDAO;
import com.kosowski.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


    // Before Spring calls this method, it looks at its signature and sees that it needs an argument of type StudentDAO
    // Spring asks itself: "Do I have any bean in my container that matches the StudentDAO type?"
    // He looks into his "bean warehouse" and finds what he needs.
    // Spring takes this existing bean and passes it as an argument when calling this method.
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            // createStudent(studentDAO);
            //readStudent(studentDAO);
            readAllStudents(studentDAO);
        };
    }

    private void readStudent(StudentDAO studentDAO) {
        Student student1 = new Student("Alicja", "Brzoza", "brzozaa@gmail.com");
        studentDAO.save(student1);
        System.out.println("Student saved successfully, ID: " + student1.getId() + "");
        Student myStudent = studentDAO.findById(student1.getId());
        System.out.println("Student found by ID: " + myStudent.getFirstName() + "");
    }

    private void createStudent(StudentDAO studentDAO){
        Student student1 = new Student("Kacper", "Walczak", "walczakk@gmail.com");
        studentDAO.save(student1);
        System.out.println("Student saved successfully, ID: " + student1.getId() + "");
        Student student2 = new Student("Maria", "Nowacka", "nowackam@o2.pl");
        studentDAO.save(student2);
        System.out.println("Student saved successfully, ID: " + student2.getId() + "");
        Student student3 = new Student("Anna", "Kowalska", "kowalskaa@onet.pl");
        studentDAO.save(student3);
        System.out.println("Student saved successfully, ID: " + student3.getId() + "");
    }

    private void readAllStudents(StudentDAO studentDAO){
        System.out.println("Getting all students from database");
        var students = studentDAO.findAll("N");
        for(var student: students){
            System.out.println(student);
        }
    }

}
