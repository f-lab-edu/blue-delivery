package com.delivery.exception;

import com.delivery.utility.ErrorCode;

public class InvalidAuthenticationException extends CustomException {

    public InvalidAuthenticationException() {
        super(ErrorCode.INVALID_AUTHENTICATION);
    }
}
