package com.kucingBerdiri.perpusApps.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kucingBerdiri.perpusApps.model.BorrowRec;
import com.kucingBerdiri.perpusApps.model.User;

public interface BorrowRecRepo extends CrudRepository<BorrowRec, Integer> {
	List<BorrowRec> findByUser(User user);
}
