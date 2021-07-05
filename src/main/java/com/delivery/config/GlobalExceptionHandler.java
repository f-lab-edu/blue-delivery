package com.delivery.config;

import static com.delivery.response.HttpResponse.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.delivery.exception.ApiException;
import com.delivery.exception.ApiExceptionEntity;
import com.delivery.exception.InvalidAuthenticationException;
import com.delivery.exception.NotFoundIdException;
import com.delivery.exception.PasswordAuthenticationException;
import com.delivery.response.ErrorCode;
import com.delivery.response.HttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionEntity> apiExceptionHandler(final ApiException ex) {
        ErrorCode error = ex.getError();
        return ResponseEntity
                .status(error.getHttpStatus())
                .body(new ApiExceptionEntity(
                        error.getStatus(),
                        error.getMessage()
                ));
    }

    // 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("errorLength", String.valueOf(ex.getFieldErrors().size()));
        for (FieldError error : ex.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity(HttpResponse.res(FAIL, errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> duplicateKeyExceptionHandler(DuplicateKeyException ex) {
        return new ResponseEntity(res(FAIL, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ResponseEntity(res(FAIL, ex.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<String> invalidAuthenticationExceptionHandler(InvalidAuthenticationException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return new ResponseEntity(res(errorCode.getStatus(), errorCode.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PasswordAuthenticationException.class)
    public ResponseEntity<String> passwordAuthenticationExceptionHandler(PasswordAuthenticationException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return new ResponseEntity(res(errorCode.getStatus(), errorCode.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundIdException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundIdException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        return new ResponseEntity(res(errorCode.getStatus(), errorCode.getMessage()), HttpStatus.NOT_FOUND);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity internalServerExceptionHandler(Exception ex) {
        return new ResponseEntity(HttpResponse.res(ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
