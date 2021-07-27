package com.bluedelivery.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EncryptUtilsTest {
    
    @Test
    void encryptedPasswordTest() {
        String password = "encryptMe@1";
        String encrypted = EncryptUtils.sha256(password);
        assertThat(password).isNotEqualTo(encrypted);
    }
}
