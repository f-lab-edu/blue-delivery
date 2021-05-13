package com.delivery.utility;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EncryptUtilsTest {

    @Test
    void encryptedPasswordTest() {
        String password = "encryptMe@1";
        String encrypted = EncryptUtils.sha256(password);
        assertThat(password).isNotEqualTo(encrypted);
    }
}