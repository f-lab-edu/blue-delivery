package com.delivery.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegexTest {

    @Test
    @DisplayName("특수문자, 숫자, 알파벳을 각 1개 이상 포함하고 8~20자 사이여야 한다.")
    void passwordRegexTest() {
        String regex = "(?=.*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣])(?=.*[0-9])(?=.*[^\\w\\s]).{8,20}";
        boolean more8 = "1a*45678".matches(regex);
        Assertions.assertThat(more8).isTrue();

        boolean includeKorean = "1가힣*45678".matches(regex);
        Assertions.assertThat(includeKorean).isTrue();

        boolean under8 = "1a*4b!7".matches(regex);
        Assertions.assertThat(under8).isFalse();

        boolean noNumber = "abcd!@#$".matches(regex);
        Assertions.assertThat(noNumber).isFalse();

        boolean onlyNumber = "12345678".matches(regex);
        Assertions.assertThat(onlyNumber).isFalse();

        boolean onlySpecial = "!@#$!@#$".matches(regex);
        Assertions.assertThat(onlySpecial).isFalse();
    }

    @Test
    @DisplayName("01X로 시작하는 10~11자리 숫자여야 한다. '-'은 포함될수도 있고 안될수도 있다.")
    void phoneRegexTest() {
        String regex = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
        boolean pass = "010-1234-4321".matches(regex);
        Assertions.assertThat(pass).isTrue();

        boolean noHyphen = "01012345678".matches(regex);
        Assertions.assertThat(noHyphen).isTrue();

        boolean notStartWith01X = "02-1234-4321".matches(regex);
        Assertions.assertThat(notStartWith01X).isFalse();

        boolean notNumber = "공일공일이삼사오륙칠팔".matches(regex);
        Assertions.assertThat(notNumber).isFalse();

        boolean wrongLength = "010-1234-567".matches(regex);
        Assertions.assertThat(wrongLength).isFalse();
    }
}
