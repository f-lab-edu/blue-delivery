package com.delivery.exception;

public class ApiExceptionEntity {
    private String errorCode;
    private String errorMessage;
    
    public ApiExceptionEntity(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
