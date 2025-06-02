package com.example.bibliotheque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bibliotheque.dto.BookRequest;
import com.example.bibliotheque.entity.Book;
import com.example.bibliotheque.repository.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book add(BookRequest book) {
        Optional<Book> b = bookRepository.findByTitle(book.title());
        if (b.isPresent()) {
            throw new IllegalArgumentException("Le livre est déja présent!");
        }
        return bookRepository.save(new Book(book.title(), book.author()));
    }

    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

}
