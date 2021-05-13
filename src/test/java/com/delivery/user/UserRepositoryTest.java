package com.delivery.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRepository.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void insertTest() {
        String email1 = "user1@email.com";
        User user1 = new User(email1, "password");

        User found = userRepository.findByEmail(user1.getEmail());
        assertThat(found).isNull();

        userRepository.insert(user1);
        found = userRepository.findByEmail(user1.getEmail());
        assertThat(found).isNotNull();

        String email2 = "user2@email.com";
        User user2 = new User(email2, "password");

        found = userRepository.findByEmail(user2.getEmail());
        assertThat(found).isNull();

        userRepository.insert(user2);
        found = userRepository.findByEmail(user2.getEmail());
        assertThat(found).isNotNull();
    }

    @Test
    void duplicateEmailTest() {
        String email = "user3@email.com";
        User user = new User(email, "password");
        userRepository.insert(user);
        assertThrows(DuplicateKeyException.class, () -> userRepository.insert(user)) ;
    }
}