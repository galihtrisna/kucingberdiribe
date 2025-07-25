package com.kucingBerdiri.perpusApps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kucingBerdiri.perpusApps.response.ApiResponse;

@RestControllerAdvice
public class AuthException {
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidLogin(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(ApiResponse.fail("Username/Password Salah"));
    }
}
