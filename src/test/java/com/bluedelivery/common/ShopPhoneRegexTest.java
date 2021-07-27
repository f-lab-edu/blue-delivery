package com.bluedelivery.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("휴대폰번호 또는 지역번호로 시작하는 전화번호인지 확인")
public class ShopPhoneRegexTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"02-373-1234", "03112341234", "0551234-1234"})
    void success(String input) {
        boolean matches = input.matches(RegexConstants.SHOP_PHONE);
        assertThat(matches).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"010-1234-1234", "056-123-123", "01051234-1234"})
    void fail(String input) {
        boolean matches = input.matches(RegexConstants.SHOP_PHONE);
        assertThat(matches).isFalse();
    }
}
