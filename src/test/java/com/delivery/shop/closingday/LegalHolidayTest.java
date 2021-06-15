package com.delivery.shop.closingday;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(transformed).isEqualTo(solar);
    }
}
