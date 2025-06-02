package com.example.bibliotheque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bibliotheque.entity.User;
import com.example.bibliotheque.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;

    
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
