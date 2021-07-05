package com.delivery.utility;

import static com.delivery.utility.HttpRes.*;

public enum ErrorCode {

    GROUP_ALREADY_EXISTS(FAIL, "groupName already exists"),
    MENU_ALREADY_EXISTS(FAIL, "menuName already exists"),
    SHOP_DOES_NOT_EXIST(FAIL, "Shop does not exist."),
    PASSWORD_NOT_MATCH(FAIL, "password not match"),
    DUPLICATE_EMAIL(FAIL, "User email duplicated."),
    DUPLICATE_NICKNAME(FAIL, "User nickname duplicated."),
    DUPLICATE_PHONE(FAIL, "User phone number duplicated."),
    USER_NOT_VALIDATED(FAIL, "User is not validated."),
    USER_NOT_FOUND(FAIL, "User is not found."),
    NOT_AUTHORIZED_ACCESS(FAIL, "Not Authorized access"),
    INVALID_AUTHENTICATION(FAIL, "Invalid Authentication");

    private final String status;
    private final String message;

    ErrorCode(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
