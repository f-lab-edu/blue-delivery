package com.delivery.shop.closingday;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LegalHolidayClosing implements ClosingDay {
    
    private static final Map<Year, Set<LocalDate>> yearly; // key의 Year은 음력년도, value는 변환된 양력 set
    
    static {
        yearly = new HashMap<>();
    }
    
    /**
     * 법정공휴일에 해당하는지 확인한다. 법정공휴일에서 일요일은 제외하도록 한다.
     * @see LegalHoliday
     * @param date 확인하고 싶은 날짜
     * @return 법정공휴일에 해당된다면 true
     */
    @Override
    public boolean isClosedAt(LocalDate date) {
        Year year = Year.of(date.getYear());
        if (!yearly.containsKey(year)) {
            updateHolidays(year);
        }
        Set<LocalDate> holidays = yearly.get(year);
        return holidays.contains(date);
    }
    
    private void updateHolidays(Year year) {
        Set<LocalDate> holidays = Arrays.stream(LegalHoliday.values())
                .map(holiday -> LegalHoliday.transformToSolar(year, holiday))
                .collect(Collectors.toSet());
        yearly.put(year, holidays);
    }
    
    
}
