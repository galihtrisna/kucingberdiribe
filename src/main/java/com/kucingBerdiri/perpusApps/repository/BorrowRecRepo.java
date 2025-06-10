package com.kucingBerdiri.perpusApps.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kucingBerdiri.perpusApps.model.Book;
import com.kucingBerdiri.perpusApps.model.BorrowRec;
import com.kucingBerdiri.perpusApps.model.User;

public interface BorrowRecRepo extends JpaRepository<BorrowRec, Integer>{
	List<BorrowRec> findByUser(User user);
	boolean existsByUserAndBookAndStatus(User user, Book book, String status);
	Optional<BorrowRec> findByDocumentCode(String documentCode);
}
