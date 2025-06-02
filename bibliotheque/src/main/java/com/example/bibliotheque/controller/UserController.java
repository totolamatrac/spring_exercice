package com.example.bibliotheque.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotheque.dto.ApiResponse;
import com.example.bibliotheque.dto.UserResponse;
import com.example.bibliotheque.entity.User;
import com.example.bibliotheque.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @GetMapping()
    @PreAuthorize("permitAll()")
    public ApiResponse<List<UserResponse>> getAll() {
        List<User> resp = userService.getAll();
        if (resp.isEmpty()) {
            return ApiResponse.error("Erreur pas de users", HttpStatus.NOT_FOUND);
        }
        List<UserResponse> respParsed = resp.stream().map(el -> new UserResponse(el.getUsername(), el.getEmail())).toList();
        return ApiResponse.success(respParsed);  
    }
}
