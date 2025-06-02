package com.example.bibliotheque.dto;

import jakarta.validation.constraints.NotNull;

public record LoanRequest(
    @NotNull
    long userId, 
    @NotNull
    long bookId
    ) {
    
}
