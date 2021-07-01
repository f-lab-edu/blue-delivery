package com.delivery.exception;

import static org.springframework.http.MediaType.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public enum ExceptionEnum {
    SHOP_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST, "S01", "Shop does not exist.", APPLICATION_JSON),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "U01", "User email duplicate.", APPLICATION_JSON),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "U02", "User nickname duplicate.", APPLICATION_JSON),
    DUPLICATE_PHONE(HttpStatus.BAD_REQUEST, "U03", "User phone number duplicate.", APPLICATION_JSON);
    
    private final HttpStatus status;
    private final String code;
    private String message;
    private final MediaType mediaType;
    
    ExceptionEnum(HttpStatus status, String code, String message, MediaType mediaType) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.mediaType = mediaType;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public MediaType getMediaType() {
        return mediaType;
    }
}
