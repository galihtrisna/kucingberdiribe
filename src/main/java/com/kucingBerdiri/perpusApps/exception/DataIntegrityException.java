package com.kucingBerdiri.perpusApps.exception;



import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kucingBerdiri.perpusApps.response.ApiResponse;

@RestControllerAdvice
public class DataIntegrityException {
	@ExceptionHandler({
	    DataIntegrityViolationException.class
	})
    public ResponseEntity<ApiResponse<String>> handleValidationErrors(DataIntegrityViolationException ex) {
		 
        return ResponseEntity.badRequest().body(ApiResponse.fail("Error DB : Data yang diinsert haruslah unique"));
    }
}


