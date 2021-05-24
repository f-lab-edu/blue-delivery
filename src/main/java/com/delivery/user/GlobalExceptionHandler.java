package com.delivery.user;

import com.delivery.config.PasswordAuthenticationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new LinkedHashMap<>();
		errors.put("errorLength", String.valueOf(ex.getFieldErrors().size()));
		for (FieldError error : ex.getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(errors.toString());
	}


	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<String> methodArgumentNotValidExceptionHandler(DuplicateKeyException ex) {
		return ResponseEntity.badRequest().body("email already exists - " + ex.getMessage());
	}

	@ExceptionHandler(PasswordAuthenticationException.class)
	public ResponseEntity<String> PasswordAuthenticationExceptionHandler(PasswordAuthenticationException ex) {
		return ResponseEntity.badRequest().body("password not match - " + ex.getMessage());
	}

}
