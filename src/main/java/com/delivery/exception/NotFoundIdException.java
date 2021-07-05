package com.delivery.exception;

import com.delivery.utility.ErrorCode;

public class NotFoundIdException extends CustomException {

    public NotFoundIdException() {
        super(ErrorCode.SHOP_DOES_NOT_EXIST);
    }
}
