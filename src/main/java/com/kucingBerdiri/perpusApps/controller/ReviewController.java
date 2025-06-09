package com.kucingBerdiri.perpusApps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kucingBerdiri.perpusApps.dtos.AddReviewDto;
import com.kucingBerdiri.perpusApps.dtos.BookReviewsDto;
import com.kucingBerdiri.perpusApps.response.ApiResponse;
import com.kucingBerdiri.perpusApps.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
	
	@Autowired
	 private ReviewService reviewService;
	 
	 @GetMapping("/{bookId}")
	 public @ResponseBody ApiResponse<List<BookReviewsDto>> getReview(@PathVariable Integer bookId){
		 List<BookReviewsDto> records = reviewService.getReviewByBookId(bookId);
		 return ApiResponse.success(records);
	 }
	 
	 @PostMapping("/{bookId}")
	 public ResponseEntity<Void> addReview(@PathVariable Integer bookId, Authentication auth, @Valid @RequestBody AddReviewDto review) {
		 reviewService.addReview(bookId, auth.getName(), review);
		 return ResponseEntity.status(HttpStatus.CREATED).build();
	 }
	 
	 @DeleteMapping("/{reviewId}")
	 public ApiResponse<String> deleteReview(@PathVariable Integer reviewId, Authentication auth) {
		 reviewService.deleteById(reviewId, auth.getName());
		 return ApiResponse.success("Ulasan berhasil dihapus");
	 }
}
