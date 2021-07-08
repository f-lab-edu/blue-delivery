package com.delivery.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;

class UserTest {
    
    User user;
    String email;
    String password;
    
    @BeforeEach
    void setup() {
        email = "my@gmail.com";
        password = "P@ssw0rd";
        user = new User(email, "nickname", "", password, LocalDate.now(), "seoul");
    }
    
    @Test
    void throwExceptionIfEmailIsNotEqual() {
        ErrorCode ex1 = assertThrows(ApiException.class, () -> user.validate("Wrong Email", password)).getError();
        assertThat(ex1).isEqualTo(ErrorCode.USER_NOT_VALIDATED);
    }
    
    @Test
    void throwExceptionIfPasswordIsNotEqual() {
        ErrorCode ex1 = assertThrows(ApiException.class, () -> user.validate(email, "Wrong Password")).getError();
        assertThat(ex1).isEqualTo(ErrorCode.USER_NOT_VALIDATED);
    }
}
