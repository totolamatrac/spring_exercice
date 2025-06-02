package com.example.bibliotheque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findByTitle(String title);
}
