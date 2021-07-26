package com.bluedelivery.user;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bluedelivery.user.domain.User;

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
    void throwExceptionIfPasswordIsNotEqual() {
        boolean result = user.validate("Wrong Password");
        assertThat(result).isFalse();
    }
}
