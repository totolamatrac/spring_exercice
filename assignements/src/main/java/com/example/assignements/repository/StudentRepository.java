package com.example.assignements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignements.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}
