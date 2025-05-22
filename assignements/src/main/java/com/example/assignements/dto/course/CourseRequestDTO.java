package com.example.assignements.dto.course;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDTO(
    @NotBlank(message = "title not blank")
    String title, 
    String description) {}
