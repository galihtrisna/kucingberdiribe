package com.kucingBerdiri.perpusApps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kucingBerdiri.perpusApps.dtos.ProfileDto;
import com.kucingBerdiri.perpusApps.model.User;
import com.kucingBerdiri.perpusApps.repository.UserRepo;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo repository;

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByUsername(username); 

        
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public boolean addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return true;
    }
    
    public ProfileDto getUserProfile(Authentication auth) {
    	User user = repository.findByUsername(auth.getName())
    			.orElseThrow(() -> new UsernameNotFoundException("User not found: " + auth.getName()));
    	
    	ProfileDto me = new ProfileDto(
    			user.getUsername(),
    			user.getFullName(),
    			user.getRole(),
    			user.getCreatedAt(),
    			user.getLastUpdatedAt()
    			);
    	
    	return me;
    }
}