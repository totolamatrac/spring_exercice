package com.example.bibliotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{}
