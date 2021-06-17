package com.delivery.shop.closingday;

import java.time.LocalDate;
import java.time.Year;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LegalHolidayClosing implements ClosingDayPolicy {
    
    private static final String CLOSING_TYPE = "LEGAL_HOLIDAY";
    @JsonIgnore
    private static final YearlyLegalHolidays YEARLY = new YearlyLegalHolidays();;
    
    public LegalHolidayClosing() {
    }
    
    // mybatis의 resultMap을 사용할때 매개변수 없는 생성자로 초기화하는 방법을 모르겠어서 임시로 사용함
    public LegalHolidayClosing(Long nothing) {
    }
    
    public static Collection<LocalDate> getYearOf(Year year) {
        return YEARLY.getYearly().get(year);
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
        return YEARLY.isHoliday(date);
    }
    
    public String getClosingType() { // static getter는 JsonMappingException 발생하므로 주의
        return CLOSING_TYPE;
    }
    
    @Override
    public String toString() {
        return "LegalHolidayClosing{}";
    }
}
