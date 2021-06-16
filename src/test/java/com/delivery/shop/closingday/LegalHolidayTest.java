package com.delivery.shop.closingday;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import net.time4j.PlainDate;
import net.time4j.calendar.ChineseCalendar;

class LegalHolidayTest {
    
    @Test
    @DisplayName("2021년 1월 1일의 음력은 2021년 2월 12일이다.")
    void lunarToSolarTest() {
        LocalDate date = LocalDate.of(2021, Month.JANUARY, 1);
        LocalDate solar = LocalDate.of(2021, Month.FEBRUARY, 12);
        ChineseCalendar of = ChineseCalendar.ofNewYear(2021)
                .plus(date.getMonthValue() - 1, ChineseCalendar.Unit.MONTHS)
                .plus(date.getDayOfMonth() - 1, ChineseCalendar.Unit.DAYS);
        PlainDate lunar = of.transform(PlainDate.axis());
        LocalDate transformed = LocalDate.of(lunar.getYear(),
                lunar.getMonth(),
                lunar.getDayOfMonth());
        assertThat(transformed).isEqualTo(solar);
    }
    
    /**
     * 2월 11일(목)~2월 13일(토) 설날 연휴 -> 15(월)
     * 6월 6일(일) 현충일 -> 7(월)
     * 8월 15일(일) 광복절 -> 16(월)
     * 10월 3일(일) 개천절 -> 4(월)
     * 10월 9일(토) 한글날 -> 11(월)
     * 12월 25일(토) 기독탄신일(크리스마스) -> 27(월)
     * @param date
     */
    @ParameterizedTest
    @ValueSource(strings = {"2021-02-15", "2021-06-07", "2021-08-16", "2021-10-04", "2021-10-11", "2021-12-27"})
    void substituteHolidayTest(String date) {
        Set<LocalDate> holidays = LegalHoliday.getSolarDays(Year.of(2021));
        LocalDate substitute = LocalDate.parse(date);
        assertThat(holidays.contains(substitute)).isTrue();
    }
}
