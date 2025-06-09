package com.kucingBerdiri.perpusApps.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kucingBerdiri.perpusApps.response.ApiResponse;

@RestControllerAdvice
public class ValidException {
	@ExceptionHandler({
	    MethodArgumentNotValidException.class
	})
    public ResponseEntity<ApiResponse<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
		 Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage())
	        );
        return ResponseEntity.badRequest()
                .body(ApiResponse.fail("Validasi gagal: " + errors.toString()));
    }
}
