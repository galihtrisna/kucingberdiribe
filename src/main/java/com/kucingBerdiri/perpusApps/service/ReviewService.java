package com.kucingBerdiri.perpusApps.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.kucingBerdiri.perpusApps.dtos.AddReviewDto;
import com.kucingBerdiri.perpusApps.dtos.BookReviewsDto;
import com.kucingBerdiri.perpusApps.dtos.UserDto;
import com.kucingBerdiri.perpusApps.model.Book;
import com.kucingBerdiri.perpusApps.model.Review;
import com.kucingBerdiri.perpusApps.model.User;
import com.kucingBerdiri.perpusApps.repository.BooksRepo;
import com.kucingBerdiri.perpusApps.repository.ReviewRepo;
import com.kucingBerdiri.perpusApps.repository.UserRepo;


@Service
public class ReviewService {
	@Autowired
    private ReviewRepo reviewRepo;
	
	@Autowired
	private BooksRepo booksRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public void addReview(Integer bookId, String username, AddReviewDto addReview){
	    Book book = booksRepo.findById(bookId)
	            .orElseThrow(() -> new RuntimeException("Book not found"));
	    User user = userRepo.findByUsername(username)
	    		.orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setContent(addReview.getContent());
        review.setRating(addReview.getRating());
		
		reviewRepo.save(review);
	}
	
	public void deleteById(Integer reviewId, String username){
	    Review review = reviewRepo.findById(reviewId)
            .orElseThrow(() -> new RuntimeException("Review not found"));
	    User user = userRepo.findByUsername(username)
	    		.orElseThrow(() -> new RuntimeException("User not found"));
	    

        boolean isOwner = review.getUser().getUsername().equals(username);
        boolean isAdmin = user.getRole().contains("LIBRARIAN");

        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("You are not allowed to delete this review");
        }

        reviewRepo.deleteById(reviewId);
	}
	
	public List<BookReviewsDto> getReviewByBookId(Integer bookId) {
		List<BookReviewsDto> reviews = reviewRepo.findByBookId(bookId).stream().map(review -> {
		    User user = review.getUser();
		    UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getFullName());

		    return new BookReviewsDto(
		        review.getId(),
		        review.getContent(),
		        review.getRating(),
		        review.getCreatedAt(),
		        review.getUpdatedAt(),
		        userDto
		    );
		}).collect(Collectors.toList());
		
        return reviews;
    }
}
