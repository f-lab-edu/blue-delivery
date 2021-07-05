package com.delivery.exception;

import com.delivery.response.ErrorCode;

public class InvalidAuthenticationException extends CustomException {

    public InvalidAuthenticationException() {
        super(ErrorCode.INVALID_AUTHENTICATION);
    }
}
