package com.example.bibliotheque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotheque.dto.ApiResponse;
import com.example.bibliotheque.dto.LoanRequest;
import com.example.bibliotheque.dto.LoanResponse;
import com.example.bibliotheque.entity.Loan;
import com.example.bibliotheque.service.LoanService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_READER')")
    public ApiResponse<LoanResponse> loanBook(@RequestBody @Valid LoanRequest req) {
        try {
            Loan resp = this.loanService.loanBook(req);
            LoanResponse respParsed = new LoanResponse(resp.getId(), resp.getUser().getEmail(),
                    resp.getBook().getTitle(), resp.getLoanDate(), resp.getReturnDateTime());
            return ApiResponse.success(respParsed);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{idLoan}/return")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ApiResponse<LoanResponse> returnBook(@PathVariable long idLoan) {

        try {
            Loan resp = this.loanService.returnBook(idLoan);
            return ApiResponse.success(new LoanResponse(resp.getId(), resp.getUser().getEmail(),
                    resp.getBook().getTitle(), resp.getLoanDate(), resp.getReturnDateTime()));
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mine")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ApiResponse<List<LoanResponse>> getAllLoanAuthentificated() {
        try {
            List<Loan> resp = loanService.getAllLoansByUser();
            List<LoanResponse> respParsed = resp.stream().map(el -> new LoanResponse(el.getId(),
                    el.getUser().getEmail(), el.getBook().getTitle(), el.getLoanDate(), el.getReturnDateTime()))
                    .toList();
            return ApiResponse.success(respParsed);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public ApiResponse<List<LoanResponse>> getAll() {
        List<Loan> resp = loanService.getAll();
        List<LoanResponse> respParsed = resp.stream().map(el -> new LoanResponse(el.getId(), el.getUser().getEmail(),
                el.getBook().getTitle(), el.getLoanDate(), el.getReturnDateTime())).toList();
        return ApiResponse.success(respParsed);
    }
}
