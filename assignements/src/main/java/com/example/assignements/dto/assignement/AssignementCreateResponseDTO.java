package com.example.assignements.dto.assignement;

import java.time.LocalDateTime;


import com.example.assignements.dto.course.CourseResponseDTO;
import com.example.assignements.dto.student.StudentResponseDTO;


public record AssignementCreateResponseDTO(
    long id, 
    String title, 
    LocalDateTime dueDate,
    CourseResponseDTO course, 
    StudentResponseDTO student) {}
