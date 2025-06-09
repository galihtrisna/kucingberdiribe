package com.kucingBerdiri.perpusApps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kucingBerdiri.perpusApps.model.Book;

public interface BooksRepo extends JpaRepository<Book, Integer>{
	List<Book> findByTitleContainingIgnoreCase(String title);
	@Query("SELECT DISTINCT b.jenisBuku FROM Book b WHERE b.jenisBuku IS NOT NULL")
    List<String> findDistinctJenisBuku();
}
