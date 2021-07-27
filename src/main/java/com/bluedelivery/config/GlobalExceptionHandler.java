package com.bluedelivery.config;

import static com.bluedelivery.common.response.HttpResponse.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.common.response.ErrorCode;
import com.bluedelivery.common.response.HttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.error("500 error ", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response(
                        ERROR,
                        ex.getMessage()
                ));
    }

}
