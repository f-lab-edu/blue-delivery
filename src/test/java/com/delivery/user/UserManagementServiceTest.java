package com.delivery.user;

import com.delivery.config.RepositoryConfigDev;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest({UserManagementService.class, UserRepository.class, RepositoryConfigDev.class})
@ActiveProfiles("dev")
class UserManagementServiceTest {

	@Autowired
	UserManagementService service;

	@Test
	void registerDuplicateEmailTest() {
		String email1 = "myEmail@email.com";
		User user1 = new User(email1, "password", "010-1234-4321", "myP@ssw0rd", LocalDate.of(2000, Month.APRIL, 1));
		assertDoesNotThrow(() -> service.register(user1));
		assertThrows(DuplicateKeyException.class, () -> service.register(user1));
	}

	@Test
	void userUpdateTest() {
		User user = new User("test1", "testName1", "010-1111-1111", "1234", LocalDate.of(2030, Month.APRIL, 1));
		service.register(user);

		UserUpdateAccountDto dto = new UserUpdateAccountDto("test1", "testName2", "010-2222-2222", "1234", LocalDate.of(2030, Month.APRIL, 1));
		service.updateAccount(dto);

		User findUser = service.getAccount(user.getEmail());

		assertThat(user.getNickname()).isNotEqualTo(findUser.getNickname());
	}
}
