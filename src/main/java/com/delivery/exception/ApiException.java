package com.delivery.exception;

import com.delivery.response.ErrorCode;

public class ApiException extends RuntimeException {

    private ErrorCode error;
    
    public ApiException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorCode getError() {
        return error;
    }
}
