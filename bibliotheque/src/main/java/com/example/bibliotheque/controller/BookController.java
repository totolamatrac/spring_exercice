package com.example.bibliotheque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotheque.dto.ApiResponse;
import com.example.bibliotheque.dto.BookRequest;
import com.example.bibliotheque.dto.BookResponse;
import com.example.bibliotheque.entity.Book;
import com.example.bibliotheque.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    
    private BookService bookService;

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<List<BookResponse>> getAll() {
        List<Book> resp = bookService.getAll();
        List<BookResponse> parseResp = resp.stream().map(book -> new BookResponse(book.getTitle(), book.getAuthor(), book.isAvailable())).toList(); 
        return ApiResponse.success(parseResp);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ApiResponse<BookResponse> add(@RequestBody @Valid BookRequest reqBook) {
        try {
            Book respBook = bookService.add(reqBook);
            return ApiResponse.success(new BookResponse(respBook.getTitle(), respBook.getAuthor(), respBook.isAvailable()));
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idBook}")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ApiResponse<String> remove(@PathVariable long idBook) {
        try {
            this.bookService.remove(idBook);
            return ApiResponse.success("Le livre a bien été supprimé");
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    


    
}
