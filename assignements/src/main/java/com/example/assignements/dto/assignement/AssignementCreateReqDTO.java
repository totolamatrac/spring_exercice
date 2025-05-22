package com.example.assignements.dto.assignement;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssignementCreateReqDTO(
    @NotBlank(message = "Title must not be blank")
    String title,
    @NotBlank(message = "DueDate must not be null") 
    LocalDateTime dueDate,
    @NotNull(message = "student ref not null") 
    long idStudent, 
    @NotNull(message = "course ref not null")
    long idCourse) {}
