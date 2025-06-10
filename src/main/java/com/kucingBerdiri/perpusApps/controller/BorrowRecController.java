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
    
    @GetMapping("/history/all")
    public ResponseEntity<?> getAllBorrowHistory(Authentication authentication) {

        List<BorrowRec> records = borrowRecService.getAllBorrowHistory();
        return ResponseEntity.ok(records);
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<?> borrowBook(
    		@PathVariable("bookId") Integer bookId,
            Authentication authentication) {
        
        String username = authentication.getName();
        String message = borrowRecService.borrowBook(bookId, username);
        return ResponseEntity.ok(message);
    }
    
    
    @PostMapping("/return/{borrowId}")
    public ResponseEntity<?> returnBook(
            @PathVariable String documentCode,
            Authentication authentication) {
        try {

            String message = borrowRecService.returnBook(documentCode);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
