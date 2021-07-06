package com.delivery.config;

import static com.delivery.response.HttpResponse.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.response.HttpResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<HttpResponse> apiExceptionHandler(final ApiException ex) {
        ErrorCode error = ex.getError();
        return ResponseEntity
                .status(error.getHttpStatus())
                .body(new HttpResponse(
                        error.getStatus(),
                        error.getMessage()
                ));
    }

    // 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("errorLength", String.valueOf(ex.getFieldErrors().size()));
        for (FieldError error : ex.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response(
                        FAIL,
                        errors.toString()
                ));
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerExceptionHandler(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response(
                        ERROR,
                        ex.getMessage()
                ));
    }

}
