package com.delivery.shop.closingday;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LegalHolidayClosing implements ClosingDayPolicy {
    
    private static final String CLOSING_TYPE = "LEGAL_HOLIDAY";
    @JsonIgnore
    private static final Map<Year, Set<LocalDate>> yearly; // key의 Year은 음력년도, value는 변환된 양력 set
    
    static {
        yearly = new HashMap<>();
        updateHolidays(Year.of(LocalDate.now().getYear()));
    }
    
    public LegalHolidayClosing() {
    }
    
    // mybatis의 resultMap을 사용할때 매개변수 없는 생성자로 초기화하는 방법을 모르겠어서 임시로 사용함
    public LegalHolidayClosing(Long nothing) {
    }
    
    /**
     * 법정공휴일에 해당하는지 확인한다. 법정공휴일에서 일요일은 제외하도록 한다.
     *
     * @param date 확인하고 싶은 날짜
     * @return 법정공휴일에 해당된다면 true
     * @see LegalHoliday
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
    
    private static void updateHolidays(Year year) {
        Set<LocalDate> holidays = Arrays.stream(LegalHoliday.values())
                .map(holiday -> LegalHoliday.transformToSolar(year, holiday))
                .collect(Collectors.toSet());
        yearly.put(year, holidays);
    }
    
    public String getClosingType() {
        return CLOSING_TYPE;
    }
    
    public static Map<Year, Set<LocalDate>> getYearly() {
        return Collections.unmodifiableMap(yearly);
    }
    
    @Override
    public String toString() {
        return "LegalHolidayClosing{"
                + LegalHolidayClosing.yearly.get(Year.of(LocalDate.now().getYear()))
                + "}";
    }
}
