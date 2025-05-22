package com.example.assignements.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequestDTO(
    @NotBlank(message = "name not blank")
    String name, 
    @NotBlank(message = "email not null")
    @Email(message = "email pattern not good")
    String email) {}
