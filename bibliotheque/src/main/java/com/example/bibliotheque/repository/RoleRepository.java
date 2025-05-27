package com.example.bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
