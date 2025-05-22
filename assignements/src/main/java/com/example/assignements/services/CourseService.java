package com.example.assignements.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.assignements.dto.course.CourseRequestDTO;
import com.example.assignements.entity.Course;
import com.example.assignements.repository.CourseRepository;

@Service
public class CourseService {
    CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course create(CourseRequestDTO course) {
        return this.courseRepo.save(new Course(course.title(), course.description()));
    }

    public List<Course> getAllCourse() {
        return this.courseRepo.findAll();
    }
}
