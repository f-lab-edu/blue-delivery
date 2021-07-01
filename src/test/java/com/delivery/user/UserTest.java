package com.delivery.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.delivery.exception.ApiException;
import com.delivery.exception.ExceptionEnum;

class UserTest {
    
    User user;
    String email;
    String password;
    
    @BeforeEach
    void setup() {
        email = "my@gmail.com";
        password = "P@ssw0rd";
        user = new User(email, "nickname", "", password, LocalDate.now());
    }
    
    @Test
    void throwIfEmailIsNotEqual() {
        ExceptionEnum ex1 = assertThrows(ApiException.class, () -> user.validate("wrong", password)).getError();
        assertThat(ex1).isEqualTo(ExceptionEnum.USER_NOT_VALIDATED);
    }
    
    @Test
    void throwIfPasswordIsNotEqual() {
        ExceptionEnum ex1 = assertThrows(ApiException.class, () -> user.validate(email, "wrong")).getError();
        assertThat(ex1).isEqualTo(ExceptionEnum.USER_NOT_VALIDATED);
    }
}
