package com.delivery.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("errorLength=" + ex.getFieldErrors().size()+"\n");
        for (FieldError error : ex.getFieldErrors()) {
            sb.append(error.getField() + " : " + error.getDefaultMessage() +"\n");
        }
        return ResponseEntity.badRequest().body(sb.toString());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(DuplicateKeyException ex) {
        return ResponseEntity.badRequest().body("email already exists - " + ex.getMessage());
    }
}
