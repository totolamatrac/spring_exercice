package com.example.bibliotheque.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
    @NotBlank
    String title,
    @NotBlank
    String author
    ) {
    
}
