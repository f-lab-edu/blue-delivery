package com.bluedelivery.domain.closingday;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyRegularClosing extends RegularClosingDay {
    
    private static final String CLOSING_TYPE = "WEEKLY_REGULAR";
    
    private DayOfWeek dayOfWeek;
    
    public WeeklyRegularClosing(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    /**
     * 정기휴무중 하나로 입력받은 날짜가 지정된 {매주 O요일}에 해당하는지 확인한다.
     * @param date 확인하고 싶은 날짜
     * @return 주기에 해당된다면 true
     */
    @Override
    public boolean isClosedAt(LocalDate date) {
        return this.dayOfWeek == date.getDayOfWeek();
    }
    
    public String getClosingType() {
        return CLOSING_TYPE;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    
    @Override
    public String toString() {
        return "WeeklyRegularClosing{"
                + "dayOfWeek=" + dayOfWeek
                + '}';
    }
}
