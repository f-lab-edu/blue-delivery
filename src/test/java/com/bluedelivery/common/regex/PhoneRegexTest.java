package com.bluedelivery.common.regex;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bluedelivery.common.RegexConstants;

@DisplayName("01X로 시작하는 10~11자리 숫자여야 한다. '-'은 포함될수도 있고 안될수도 있다.")
public class PhoneRegexTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"010-1234-4321", "01012345678"})
    void phoneRegexTrueTest(String input) {
        Assertions.assertThat(input.matches(RegexConstants.PHONE)).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"02-1234-4321", "공일공일이삼사오륙칠팔", "010-1234-567"})
    void phoneRegexFalseTest(String input) {
        Assertions.assertThat(input.matches(RegexConstants.PHONE)).isFalse();
    }
}
