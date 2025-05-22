package com.example.assignements.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignements.dto.course.CourseRequestDTO;
import com.example.assignements.dto.course.CourseResponseDTO;
import com.example.assignements.entity.Course;
import com.example.assignements.services.CourseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public CourseResponseDTO create(@RequestBody @Valid CourseRequestDTO course) {
        Course save = this.courseService.create(course);
        return new CourseResponseDTO(save.getId(), save.getTitle(), save.getDescription());
    }

    @GetMapping()
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> resp = this.courseService.getAllCourse();
        return resp.stream().map(c -> new CourseResponseDTO(c.getId(), c.getTitle(), c.getDescription())).toList();
    }
    
    
}
