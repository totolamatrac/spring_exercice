package com.example.assignements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assignements.entity.Assignement;

public interface AssignementRepository extends JpaRepository<Assignement, Long> {}
