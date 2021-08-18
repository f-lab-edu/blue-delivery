package com.bluedelivery.domain.closingday;

import static com.bluedelivery.domain.closingday.LocalDateTimeConverter.toLocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RegularClosingPolicyTest {
    
    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 16, 17, 18, 19})
    @DisplayName("매주 일요일을 쉬는 날로 지정하고, 2021/08/13일(일요일)이 닫았는지 확인시 true, 나머지 일에는 false를 리턴한다.")
    void weeklyRegularClosingTest(int day) {
        WeeklyRegularClosing weekly = new WeeklyRegularClosing(DayOfWeek.SUNDAY);
        boolean closed = weekly.isClosed(toLocalDateTime(LocalDate.of(2021, Month.JUNE, day)));
        
        if (day == 13) {
            assertThat(closed).isTrue();
        } else {
            assertThat(closed).isFalse();
        }
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 7, 14, 21, 27, 28, 29})
    @DisplayName("매달 마지막주 월요일을 쉬는 날로 지정하고, 2021/06/28일(마지막주 월요일)을 제외한 모든 6월은 false를 리턴한다.")
    void cyclicLastWeekTest(int day) {
        CyclicRegularClosing cyclic = new CyclicRegularClosing(CyclicRegularClosing.Cycle.LAST, DayOfWeek.MONDAY);
        LocalDateTime date = LocalDateTime.of(2021, Month.JUNE, day, 0, 0, 0);
        boolean isClosed = cyclic.isClosed(date);
        if (day == 28) {
            assertThat(isClosed).isTrue();
        } else {
            assertThat(isClosed).isFalse();
        }
    }
    
    @Test
    @DisplayName("시작하는 주가 일요일 하루인 2021/08/01은 전 달의 마지막 주 일요일로 간주된다.")
    void weekUnder4daysIsLastWeekTest() {
        CyclicRegularClosing cyclic = new CyclicRegularClosing(CyclicRegularClosing.Cycle.LAST, DayOfWeek.SUNDAY);
        LocalDate date = LocalDate.of(2021, Month.AUGUST, 1);
        boolean isClosed = cyclic.isClosed(toLocalDateTime(date));
        assertThat(isClosed).isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(ints = {16, 1, 2, 7, 8, 9, 10, 15, 17, 22, 23, 24, 28, 29, 30})
    @DisplayName("2021/06 의 세번째 수요일은 16일 하루만 존재한다.")
    void thirdWeekTest(int day) {
        CyclicRegularClosing cyclic = new CyclicRegularClosing(CyclicRegularClosing.Cycle.THIRD, DayOfWeek.WEDNESDAY);
        LocalDate date = LocalDate.of(2021, Month.JUNE, day);
        boolean isClosed = cyclic.isClosed(toLocalDateTime(date));
        if (day == 16) {
            assertThat(isClosed).isTrue();
        } else {
            assertThat(isClosed).isFalse();
        }
    }
    
    @Test
    @DisplayName("ISO기준, 첫 주가 4일 미만이면 저번달 마지막주로 간주하고, 값은 당월 0주차로 표현된다. ")
    void numberOfWeekTest() {
        LocalDate today = LocalDate.of(2021, Month.AUGUST, 1); // 2021.08.01은 일요일이고 2일은 새로운 주다.
        TemporalField temporalField = WeekFields.ISO.weekOfMonth();
        int numberOfWeek = today.get(temporalField);
        assertThat(numberOfWeek).isEqualTo(0);
    }
    
    void findLastWeek() {
        // 2021.08월은 5주차 까지 있다.
        LocalDate today = LocalDate.of(2021, Month.AUGUST, 1);
        TemporalField temporalField = WeekFields.ISO.weekOfMonth();
        
        // 1. 주어진 LocalDate의 달에서 마지막날을 뽑아낸 후, 그게 몇주차인지 계산하면 마지막 주가 됌.
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        int lastWeekOfMonth = lastDayOfMonth.get(temporalField);
        System.out.println("lastWeekOfMonth = " + lastWeekOfMonth);
        
        
        // 2. YeaerMonth를 사용
        YearMonth currentYearMonth = YearMonth.from(today);
        int weeks = currentYearMonth
                .atEndOfMonth()
                .get(temporalField);
        System.out.println("weeks = " + weeks);
    }
}
