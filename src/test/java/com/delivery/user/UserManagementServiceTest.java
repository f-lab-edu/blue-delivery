package com.delivery.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest({UserManagementService.class, UserRepository.class})
class UserManagementServiceTest {

    @Autowired
    UserManagementService service;

    @Test
    void registerDuplicateEmailTest() {
        String email1 = "myEmail@email.com";
        User user1 = new User(email1, "password");
        assertDoesNotThrow(() -> service.register(user1));
        assertThrows(DuplicateKeyException.class, () -> service.register(user1));
    }
}