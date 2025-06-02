package com.example.bibliotheque.dto;

import org.springframework.http.HttpStatus;

public class ApiError {
    private String message;
    private HttpStatus status;
    private int statusCode;
    
    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.statusCode = status.value();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
