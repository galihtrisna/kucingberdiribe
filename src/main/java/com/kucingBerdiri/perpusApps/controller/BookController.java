package com.kucingBerdiri.perpusApps.controller;

import com.kucingBerdiri.perpusApps.response.ApiResponse;
import com.kucingBerdiri.perpusApps.service.BookService;


import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kucingBerdiri.perpusApps.dtos.AddBookRequest;
import com.kucingBerdiri.perpusApps.model.Book;


@Controller 
@RequestMapping(path="/book")
public class BookController {
	@Autowired
    private BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }
	
	@GetMapping(path="/all")
	public @ResponseBody ApiResponse<Iterable<Book>> getAllBooks() {
	    return ApiResponse.success(bookService.getAllBook());
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<Void> addBook(@Valid @RequestBody AddBookRequest request, Authentication authentication) {
		Book book = new Book();
		book.setTitle(request.getTitle());
	    book.setAuthor(request.getAuthor());
	    book.setPublisher(request.getPublisher());
	    book.setYear(request.getYear());
	    book.setIsbn(request.getIsbn());
	    book.setThumbnail(request.getThumbnail());
	    book.setStocks(request.getStocks());
	    book.setDigitalAvail(request.getDigitalAvail());
	    book.setJenisBuku(request.getJenisBuku());
	    book.setPages(request.getPages());

		bookService.saveBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(path = "/{bookId}")
	public @ResponseBody ApiResponse<Book> getIdBook(@PathVariable("bookId") Integer bookId) {
	    Book dataBook = bookService.findById(bookId);

	    return ApiResponse.success(dataBook);
	}
	
	@DeleteMapping(path = "/{bookId}")
	public @ResponseBody ApiResponse<String> deleteBook(@PathVariable("bookId") Integer bookId) {
		bookService.deleteById(bookId);
	    return ApiResponse.success("Buku berhasil dihapus");
	}
	
	@PutMapping(path = "/{bookId}")
	public @ResponseBody ApiResponse<?> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody Book updatedBook) {
        Book updateBook = bookService.editById(bookId, updatedBook);
        return ApiResponse.success(updateBook);
    }
	
	
	@GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title) {
		System.out.println(title);
        List<Book> result = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(result);
    }
	
	@GetMapping("/jenis")
    public ResponseEntity<List<String>> getAllJenisBuku() {
        List<String> jenisList = bookService.getAllDistinctJenisBuku();
        return ResponseEntity.ok(jenisList);
    }
	
}
