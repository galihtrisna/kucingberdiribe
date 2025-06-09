package com.kucingBerdiri.perpusApps.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public String borrowBook(Integer bookId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookService.findById(bookId);
                

        if (book.getStocks() <= 0) {
            throw new RuntimeException("Stok buku habis.");
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
                .orElseThrow(() -> new RuntimeException("User not found"));
        return borrowRecRepository.findByUser(user);
    }


    public String returnBook(Integer borrowId, String username) {
        BorrowRec record = borrowRecRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (!record.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized return attempt.");
        }

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