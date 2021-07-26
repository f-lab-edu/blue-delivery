package com.bluedelivery.exception;

import com.bluedelivery.response.ErrorCode;

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
