package com.example.assignements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignements.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {}
