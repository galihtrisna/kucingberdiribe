package com.kucingBerdiri.perpusApps.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kucingBerdiri.perpusApps.exception.CustomBookException;
import com.kucingBerdiri.perpusApps.model.Book;
import com.kucingBerdiri.perpusApps.model.BorrowRec;
import com.kucingBerdiri.perpusApps.model.User;
import com.kucingBerdiri.perpusApps.repository.BorrowRecRepo;
import com.kucingBerdiri.perpusApps.repository.UserRepo;


@Service
public class BookRecService {

    @Autowired
    private BorrowRecRepo borrowRecRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepo userRepository;
    
    
    public List<BorrowRec> getAllBorrowHistory() {
        return borrowRecRepository.findAll();
    }



    public String borrowBook(Integer bookId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookService.findById(bookId);
        
       
        
        if (book.getStocks() <= 0) {
            throw new CustomBookException("Stok buku habis.");
        }
        
        if (borrowRecRepository.existsByUserAndBookAndStatus(user, book, "BORROWED")) {
            throw new CustomBookException("Anda sudah meminjam buku ini dan belum mengembalikannya.");
        }
       

        BorrowRec borrowRec = new BorrowRec();
        borrowRec.setUser(user);
        borrowRec.setBook(book);
        borrowRec.setStartBorrow(LocalDateTime.now());
        borrowRec.setEndBorrow(LocalDateTime.now().plusDays(7));
        borrowRec.setStatus("BORROWED");
        borrowRec.setJenis(book.getJenisBuku());
        borrowRec.setDocumentCode(generateDocumentCode(user, book));

        borrowRecRepository.save(borrowRec);

        book.setStocks(book.getStocks() - 1);
        bookService.saveBook(book);

        return "Peminjaman berhasil.";
    }


    public List<BorrowRec> getBorrowHistoryByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomBookException("User not found"));
        return borrowRecRepository.findByUser(user);
    }


    public String returnBook(String documentCode) {
        BorrowRec record = borrowRecRepository.findByDocumentCode(documentCode)
                .orElseThrow(() -> new CustomBookException("Borrow record not found"));


        if (!"BORROWED".equals(record.getStatus())) {
            throw new RuntimeException("Book is not currently borrowed.");
        }

        record.setStatus("RETURNED");
        record.setEndBorrow(LocalDateTime.now());
        borrowRecRepository.save(record);

        Book book = record.getBook();
        book.setStocks(book.getStocks() + 1);
        bookService.saveBook(book);

        return "Pengembalian berhasil.";
    }

    private String generateDocumentCode(User user, Book book) {
        return "DOC-" + user.getId() + "-" + book.getId() + "-" + System.currentTimeMillis();
    }
}