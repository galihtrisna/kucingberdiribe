package com.kucingBerdiri.perpusApps.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kucingBerdiri.perpusApps.dtos.LoginUserDto;
import com.kucingBerdiri.perpusApps.model.User;
import com.kucingBerdiri.perpusApps.response.ApiResponse;
import com.kucingBerdiri.perpusApps.service.JwtService;
import com.kucingBerdiri.perpusApps.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping(path = "/auth")
public class UserController {

    @Autowired
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> addBook(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/accesstoken")
    public ResponseEntity<ApiResponse<String>> authenticateAndGetToken(@Valid @RequestBody LoginUserDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
            String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(ApiResponse.success(token));
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
