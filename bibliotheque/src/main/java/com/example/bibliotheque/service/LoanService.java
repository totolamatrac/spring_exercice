package com.example.bibliotheque.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.bibliotheque.dto.LoanRequest;
import com.example.bibliotheque.entity.Book;
import com.example.bibliotheque.entity.Loan;
import com.example.bibliotheque.entity.User;
import com.example.bibliotheque.repository.BookRepository;
import com.example.bibliotheque.repository.LoanRepository;
import com.example.bibliotheque.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService {
    
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private LoanRepository loanRepository;

    public Loan loanBook(LoanRequest req) {
        User user = userRepository.findById(req.userId()).orElseThrow(() -> new IllegalArgumentException("User introuvable !"));
        Book book = bookRepository.findById(req.bookId()).orElseThrow(() -> new IllegalArgumentException("Livre introuvable"));
        if (!book.isAvailable()) {
            throw new IllegalArgumentException("Le livre n'est pas disponible!");
        }
        book.setAvailable(false);
        this.bookRepository.save(book);
        return this.loanRepository.save(new Loan(user, book));
    }

    public Loan returnBook(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Location non trouvé!"));
        Book b = loan.getBook();
        b.setAvailable(true);
        bookRepository.save(b);
        return loan;
    }

    public List<Loan> getAllLoansByUser() {
        String curentUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(curentUserEmail).orElseThrow(() -> new IllegalArgumentException("Erreur user!"));

        Optional<List<Loan>> resp = loanRepository.findAllByUser(user);
        return resp.isPresent() ? resp.get() : new ArrayList<>();
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }
}
