package com.example.bibliotheque.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotheque.dto.ApiResponse;
import com.example.bibliotheque.dto.LoginRequest;
import com.example.bibliotheque.dto.RegisterRequest;
import com.example.bibliotheque.dto.UserResponse;
import com.example.bibliotheque.entity.User;
import com.example.bibliotheque.service.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest req) {
        try {
            String resp = authService.login(req);
            return ResponseEntity.ok("Bienvenue " + resp);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body("Identifiants incorrects");
        }
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody @Valid RegisterRequest req) {
        try {
            UserResponse resp = this.authService.register(req);
            return ApiResponse.success(resp);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @PreAuthorize("permitAll()")
    public ApiResponse<List<UserResponse>> getAll() {
        List<User> resp = authService.getAll();
        if (resp.isEmpty()) {
            return ApiResponse.error("Erreur pas de users", HttpStatus.NOT_FOUND);
        }
        List<UserResponse> respParsed = resp.stream().map(el -> new UserResponse(el.getUsername(), el.getEmail())).toList();
        return ApiResponse.success(respParsed);
        
    }
    
}
