package com.bluedelivery.domain.closingday;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  법정공휴일
 */
@Entity
@DiscriminatorValue("LH")
public class LegalHolidayClosing extends ClosingPolicy {
    
    @Transient
    private static final YearlyLegalHolidays YEARLY = new YearlyLegalHolidays();
    
    public LegalHolidayClosing() {
    }
    
    public static Collection<LocalDate> getHolidaysOf(Year year) {
        return YEARLY.getYearly().get(year);
    }
    
    /**
     * 법정공휴일에 해당하는지 확인한다. 법정공휴일에서 일요일은 제외하도록 한다.
     *
     * @param date 확인하고 싶은 날짜
     * @return 법정공휴일에 해당된다면 true
     * @see LegalHoliday
     */
    public boolean isClosed(LocalDateTime date) {
        return YEARLY.isHoliday(date.toLocalDate());
    }
    
    @Override
    public String toString() {
        return "LegalHolidayClosing{}";
    }
}
