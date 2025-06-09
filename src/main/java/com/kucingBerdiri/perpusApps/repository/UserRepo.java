package com.kucingBerdiri.perpusApps.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.kucingBerdiri.perpusApps.model.User;

public interface UserRepo extends CrudRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
