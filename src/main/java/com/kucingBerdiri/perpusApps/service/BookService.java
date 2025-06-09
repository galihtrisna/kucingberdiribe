package com.kucingBerdiri.perpusApps.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kucingBerdiri.perpusApps.model.Book;
import com.kucingBerdiri.perpusApps.repository.BooksRepo;


@Service
public class BookService {
	@Autowired
    private BooksRepo booksRepo;
	
	public Iterable<Book> getAllBook(){
		return booksRepo.findAll();
	}
	
	
	public void saveBook(Book book){
		booksRepo.save(book);
	}
	
	
	public Book findById(Integer id){
		Book book = booksRepo.findById(id)
		        .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));
		return book;
	}
	
	
	public void deleteById(Integer id){
		booksRepo.deleteById(id);
	}
	
	public Book editById(Integer id, Book bookEdit) {
		Book optionalBook = booksRepo.findById(id)
		        .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        

       
        if (bookEdit.getIsbn() != null) {
        	optionalBook.setIsbn(bookEdit.getIsbn());
        }
        
        if (bookEdit.getTitle() != null) {
        	optionalBook.setTitle(bookEdit.getTitle());
        }
        
        if (bookEdit.getAuthor() != null) {
        	optionalBook.setAuthor(bookEdit.getAuthor());
        }
        
        if (bookEdit.getPublisher() != null) {
        	optionalBook.setPublisher(bookEdit.getPublisher());
        }
        
        if (bookEdit.getYear() != null) {
        	optionalBook.setYear(bookEdit.getYear());
        }
        
        if (bookEdit.getStocks() != null) {
        	optionalBook.setStocks(bookEdit.getStocks());
        }

        if (bookEdit.getThumbnail() != null) {
        	optionalBook.setThumbnail(bookEdit.getThumbnail());
        }
        
        Book savedBook = booksRepo.save(optionalBook);
        return savedBook;
	}
	
	
	public List<Book> searchBooksByTitle(String title) {
        return booksRepo.findByTitleContainingIgnoreCase(title);
    }
	
	public List<String> getAllDistinctJenisBuku() {
        return booksRepo.findDistinctJenisBuku();
    }
}
