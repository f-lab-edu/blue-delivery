package com.bluedelivery.domain.closingday;

import static com.bluedelivery.domain.closingday.LocalDateTimeConverter.toLocalDateTime;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LegalHolidayClosingTest {
    
    LegalHolidayClosing holiday = new LegalHolidayClosing();
    
    @Test
    void holidayTest() {
        boolean closed = holiday.isClosed(toLocalDateTime(LocalDate.of(2021, Month.MAY, 5)));
        assertThat(closed).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 24, 26, 27})
    @DisplayName("2021년 구정은 양력으로 2월 11~13 +15일. (2020-01-24~26 +27, 2019-02-04~06)")
    void lunarHolidayTesT(int day) {
        // 2021년
        boolean closed = holiday.isClosed(toLocalDateTime(LocalDate.of(2021, Month.FEBRUARY, day)));
        if (day >= 11 && day <= 13 || day == 15) {
            assertThat(closed).isTrue();
        } else {
            assertThat(closed).isFalse();
        }
        
        // 2020년
        closed = holiday.isClosed(toLocalDateTime(LocalDate.of(2020, Month.JANUARY, day)));
        if (day >= 24 && day <= 26 || day == 27) {
            assertThat(closed).isTrue();
        } else {
            assertThat(closed).isFalse();
        }
        
        // 2019년
        closed = holiday.isClosed(toLocalDateTime(LocalDate.of(2019, Month.FEBRUARY, day)));
        if (day >= 4 && day <= 6) {
            assertThat(closed).isTrue();
        } else {
            assertThat(closed).isFalse();
        }
    }
    
    @Disabled
    @Test
    @DisplayName("법정공휴일 업데이트는 1년에 한번만 일어나야 한다.")
    void multiThread() {
        for (int i = 0; i < 10000; i++) {
            // isClosedAt()이 사용하는 updateHolidaysIfNotExist()에 System.out 찍어서 확인
            new Thread(() -> holiday.isClosed(toLocalDateTime(LocalDate.now()).plusYears(1))).start();
        }
    }
}
