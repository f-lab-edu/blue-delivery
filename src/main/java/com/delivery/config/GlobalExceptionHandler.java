package com.delivery.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.delivery.exception.ApiException;
import com.delivery.exception.ApiExceptionEntity;
import com.delivery.exception.ExceptionEnum;
import com.delivery.exception.InvalidAuthenticationException;
import com.delivery.exception.NotFoundIdException;
import com.delivery.exception.PasswordAuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionEntity> apiExceptionHandler(final ApiException ex) {
        ExceptionEnum error = ex.getError();
        return ResponseEntity
                .status(error.getStatus())
                .body(new ApiExceptionEntity(
                        error.getCode(),
                        error.getMessage()
                ));
    }
    
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
    public ResponseEntity<String> duplicateKeyExceptionHandler(DuplicateKeyException ex) {
        return ResponseEntity.badRequest().body("key already exists - " + ex.getMessage());
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("invalid : " + ex.toString());
    }
    
    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<String> invalidAuthenticationExceptionHandler(InvalidAuthenticationException ex) {
        return ResponseEntity.badRequest().build();
    }
    
    @ExceptionHandler(PasswordAuthenticationException.class)
    public ResponseEntity<String> passwordAuthenticationExceptionHandler(PasswordAuthenticationException ex) {
        return ResponseEntity.badRequest().body("password not match - " + ex.getMessage());
    }

    @ExceptionHandler(NotFoundIdException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundIdException ex) {
        return ResponseEntity.notFound().build();
    }

}
