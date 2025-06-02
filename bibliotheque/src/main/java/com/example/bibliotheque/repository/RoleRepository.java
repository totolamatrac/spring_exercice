package com.example.bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.entity.Role;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByName(String name);
}
