package com.delivery.shop.closingday;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TemporaryClosingTest {
    
    
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 14, 15, 16, 17, 21, 22, 23, 24, 25})
    @DisplayName("from과 to 사이의 날짜만 휴일로 간주된다.")
    void test(int day) {
        LocalDate date = LocalDate.of(2021, Month.JUNE, day);
        int fromDay = 14;
        int toDay = 21;
        LocalDate from = LocalDate.of(2021, Month.JUNE, fromDay);
        LocalDate to = LocalDate.of(2021, Month.JUNE, toDay);
        
        TemporaryClosing temporary = new TemporaryClosing(to, from); // 반대로 입력해도 시간 순서대로 from-to를 정렬해준다.
        boolean closed = temporary.isClosedAt(date);
        if (day >= fromDay && day <= toDay) {
            assertThat(closed).isTrue();
        } else {
            assertThat(closed).isFalse();
        }
        
        LocalDate minus1Year = date.minusYears(1);
        closed = temporary.isClosedAt(minus1Year);
        assertThat(closed).isFalse();
    }
}
