package com.example.bibliotheque.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bibliotheque.dto.LoginRequest;
import com.example.bibliotheque.dto.RegisterRequest;
import com.example.bibliotheque.dto.UserResponse;
import com.example.bibliotheque.entity.Role;
import com.example.bibliotheque.entity.User;
import com.example.bibliotheque.repository.RoleRepository;
import com.example.bibliotheque.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public String login(LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );
        return req.email();
    }

    public UserResponse register(RegisterRequest req) {
        if (this.userRepository.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("Cet email existe déja!");
        }
        
        Role role = roleRepository.findByName("ROLE_" + req.role()).orElseThrow(() -> new RuntimeException("Rôle introuvable"));
        
        User user = new User(req.username(), req.email(), passwordEncoder.encode(req.password()), role);
        User save = this.userRepository.save(user);
        return new UserResponse(save.getUsername(), save.getEmail());
    } 

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
