package com.example.bibliotheque.dto;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private T data;
    private ApiError error;

    private ApiResponse(T data, ApiError erorr) {
        this.data = data;
        this.error = erorr;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(data, null);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return new ApiResponse<T>(null, new ApiError(message, status));
    }

    public T getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }

    
}
