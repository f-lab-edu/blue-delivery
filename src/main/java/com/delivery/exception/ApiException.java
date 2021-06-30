package com.delivery.exception;

public class ApiException extends RuntimeException {
    private ExceptionEnum error;
    
    public ApiException(ExceptionEnum error) {
        super(error.getMessage());
        this.error = error;
    }
    
    public ExceptionEnum getError() {
        return this.error;
    }
}
