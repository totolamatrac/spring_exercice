package com.example.bibliotheque.dto;

import java.time.LocalDateTime;

public record LoanResponse(long id, String userEmail, String bookTitle, LocalDateTime loanDate, LocalDateTime returnDate) {}
