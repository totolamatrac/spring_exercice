package com.example.note_eleves.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.note_eleves.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String name);
};
