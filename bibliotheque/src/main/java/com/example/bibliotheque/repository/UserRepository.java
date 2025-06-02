package com.example.bibliotheque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.entity.Loan;
import com.example.bibliotheque.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);
}