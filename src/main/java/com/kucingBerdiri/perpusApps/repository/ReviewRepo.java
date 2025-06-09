package com.kucingBerdiri.perpusApps.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kucingBerdiri.perpusApps.model.Review;

public interface ReviewRepo extends CrudRepository<Review, Integer> {
	List<Review> findByBookId(Integer bookId);
}