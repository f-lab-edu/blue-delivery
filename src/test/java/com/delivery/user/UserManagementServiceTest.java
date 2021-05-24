package com.delivery.user;

import com.delivery.config.RepositoryConfigDev;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest({UserManagementService.class, UserRepository.class, RepositoryConfigDev.class})
@ActiveProfiles("dev")
class UserManagementServiceTest {

    @Autowired
    UserManagementService service;

    User user;
    String email;
    String password;

    @BeforeEach
    void addUser() {
        email = "myEmail@email.com";
        password = "P@ssw0rd!";
        user = new User(email, password);
        assertDoesNotThrow(() -> service.register(user));

    }

    @Test
    void registerDuplicateEmailTest() {
        assertThrows(DuplicateKeyException.class, () -> service.register(user));
    }

    @Test
    void deleteAccountNotExistsTest() {
        DeleteAccountDto dto = new DeleteAccountDto(email, password);
        assertDoesNotThrow(() -> service.deleteAccount(dto));
        assertThrows(IllegalArgumentException.class, () -> service.deleteAccount(dto));
    }

}
