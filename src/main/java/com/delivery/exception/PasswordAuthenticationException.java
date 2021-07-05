package com.delivery.exception;

import com.delivery.response.ErrorCode;

public class PasswordAuthenticationException extends CustomException {

    public PasswordAuthenticationException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}
