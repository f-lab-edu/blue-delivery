package com.bluedelivery.common.regex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.bluedelivery.common.EncryptUtils;

class EncryptUtilsTest {
    
    @Test
    void encryptedPasswordTest() {
        String password = "encryptMe@1";
        String encrypted = EncryptUtils.sha256(password);
        assertThat(password).isNotEqualTo(encrypted);
    }
}
