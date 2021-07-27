package com.bluedelivery.common.regex;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bluedelivery.common.RegexConstants;

@DisplayName("특수문자, 숫자, 알파벳을 각 1개 이상 포함하고 8~20자 사이여야 한다.")
public class PasswordRegexTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"1a*45678", "1가힣*45678"})
    void passwordRegexTrueTest(String input) {
        Assertions.assertThat(input.matches(RegexConstants.PASSWORD)).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"1a*4b!7", "abcd!@#$", "12345678", "!@#$!@#$"})
    void passwordRegexFalseTest(String input) {
        Assertions.assertThat(input.matches(RegexConstants.PASSWORD)).isFalse();
    }
}
