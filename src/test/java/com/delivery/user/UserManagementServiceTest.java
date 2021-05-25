package com.delivery.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class UserManagementServiceTest {

	UserManagementService service = new UserManagementService(new UserRepositoryHashMap());

    String email = "myEmail@email.com";
    String password = "P@ssw0rd!";
    UserRegisterDto dto = new UserRegisterDto(email, "nickname", "010-1234-5676", password, password, LocalDate.now().minusDays(1));

    @BeforeEach
    void setup() {
        assertDoesNotThrow(() -> service.register(dto));
    }

    @Test
    void registerDuplicateEmailTest() {
        assertThrows(DuplicateKeyException.class, () -> service.register(dto));
    }

    @Test
    void deleteAccountNotExistsTest() {
        DeleteAccountDto dto = new DeleteAccountDto(email, password);
        assertDoesNotThrow(() -> service.deleteAccount(dto));
        assertThrows(IllegalArgumentException.class, () -> service.deleteAccount(dto));
    }

    @Test
    void userUpdateTest() {
        UserUpdateAccountDto dto = new UserUpdateAccountDto(email, "testName2", "010-2222-2222", password, LocalDate.of(2030, Month.APRIL, 1));
        service.updateAccount(dto);
    }

}
