package com.kucingBerdiri.perpusApps.controller;

import com.kucingBerdiri.perpusApps.service.BookRecService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.kucingBerdiri.perpusApps.model.BorrowRec;

@Controller
@RequestMapping(path = "/borrow")
public class BorrowRecController {

    @Autowired
    private BookRecService borrowRecService;

    BorrowRecController(BookRecService borrowRecService) {
        this.borrowRecService = borrowRecService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<BorrowRec>> getBorrowHistory(Authentication authentication) {
        String username = authentication.getName();
        List<BorrowRec> records = borrowRecService.getBorrowHistoryByUser(username);
        return ResponseEntity.ok(records);
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<?> borrowBook(
            @PathVariable Integer bookId,
            Authentication authentication) {
        try {

            String username = authentication.getName();
            String message = borrowRecService.borrowBook(bookId, username);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/return/{borrowId}")
    public ResponseEntity<?> returnBook(
            @PathVariable Integer borrowId,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            String message = borrowRecService.returnBook(borrowId, username);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
