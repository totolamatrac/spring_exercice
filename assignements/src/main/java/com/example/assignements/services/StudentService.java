package com.example.assignements.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.assignements.dto.student.StudentRequestDTO;
import com.example.assignements.entity.Student;
import com.example.assignements.repository.StudentRepository;

@Service
public class StudentService {
    
    private StudentRepository studentRepo;

    public StudentService(StudentRepository stRepo) {
        this.studentRepo = stRepo;
    }

    public Student createStudent(StudentRequestDTO student) {
        return this.studentRepo.save(new Student(student.name(), student.email()));
    }

    public List<Student> getAllStudents() {
        return this.studentRepo.findAll();
    }
}
