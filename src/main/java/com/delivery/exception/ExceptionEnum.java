package com.delivery.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {
    SHOP_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST, "S01", "Shop does not exist."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "U01", "User email duplicated."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "U02", "User nickname duplicated."),
    DUPLICATE_PHONE(HttpStatus.CONFLICT, "U03", "User phone number duplicated."),
    USER_NOT_VALIDATED(HttpStatus.BAD_REQUEST, "U04", "User is not validated."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "U05", "User is not found."),
    NOT_AUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "U06", "Not Authorized access");
    
    private final HttpStatus status;
    private final String code;
    private final String message;
    
    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
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
}
