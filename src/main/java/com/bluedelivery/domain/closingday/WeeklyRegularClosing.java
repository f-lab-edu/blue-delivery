package com.bluedelivery.domain.closingday;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *  매주 정기 휴무 정책
 */
@Entity
@DiscriminatorValue("WEEKLY")
public class WeeklyRegularClosing extends ClosingPolicy {
    
    private DayOfWeek dayOfWeek;
    
    public WeeklyRegularClosing() {
    
    }
    
    public WeeklyRegularClosing(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    /**
     * 정기휴무중 하나로 입력받은 날짜가 지정된 {매주 O요일}에 해당하는지 확인한다.
     * @param date 확인하고 싶은 날짜
     * @return 주기에 해당된다면 true
     */
    public boolean isClosed(LocalDateTime date) {
        return this.dayOfWeek == date.getDayOfWeek();
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
