package com.bluedelivery.domain.closingday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("CYCLE")
public class CyclicRegularClosing extends ClosingPolicy {
    
    public enum Cycle {
        FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5), LAST(0);
    
        private int cycle;
    
        Cycle(int cycle) {
            this.cycle = cycle;
        }
    
        public int getCycleAsInt() {
            return cycle;
        }
    
    }
    
    public CyclicRegularClosing() {
    
    }
    
    private Cycle cycle;
    private DayOfWeek dayOfWeek;
    
    @Transient
    private TemporalField weekFields; // 외부에서 입력받는 LocalDate 의 주(첫째주, 둘째주, ...)를 계산하기 위한 기준
    
    public CyclicRegularClosing(Cycle cycle, DayOfWeek dayOfWeek) {
        this.cycle = cycle;
        this.dayOfWeek = dayOfWeek;
        this.weekFields = WeekFields.ISO.weekOfMonth();
    }
    
    /**
     * 정기휴무중 하나로 입력받은 날짜가 {[첫째주, ..., 마지막주] O요일}에 해당되는지 검사한다.
     *
     * @param date 확인하고 싶은 날짜
     * @return 주기에 해당되는 날짜면 true
     */
    public boolean isClosed(LocalDateTime date) {
        return checkDayOfWeek(date.toLocalDate()) && isCyclicWeek(date.toLocalDate());
    }
    
    /**
     * 입력받은 LocalDate의 주 == 입력받은 LocalDate의 Month의 마지막 주인지 확인
     * 월을 시작하는 주의 일수가 4일 미만이면 전 달의 마지막주로 취급하고 값은 0이 된다. 그러므로 'last'와 '0'을 검사한다.
     * (ex. 2021/08/01은 일요일이고 그 주의 유일한 날이다. (4일 미만이므로 7월의 마지막 주로 간주한다.)
     */
    private boolean isLastWeek(LocalDate date) {
        if (!checkDayOfWeek(date) || !(this.cycle == Cycle.LAST)) {
            return false;
        }
        
        YearMonth currentYearMonth = YearMonth.from(date);
        int numberOfWeek = getNumberOfWeek(date);
        int last = getNumberOfWeek(currentYearMonth.atEndOfMonth());
        return numberOfWeek == last || numberOfWeek == 0;
    }
    
    private boolean isCyclicWeek(LocalDate date) {
        return isLastWeek(date)
                || getNumberOfWeek(date) == this.cycle.getCycleAsInt();
    }
    
    private boolean checkDayOfWeek(LocalDate date) {
        return date.getDayOfWeek() == this.dayOfWeek;
    }
    
    // 주어진 날짜가 해당 월의 몇번째 주인지 리턴한다.
    private int getNumberOfWeek(LocalDate date) {
        return date.get(weekFields);
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    
    @Override
    public String toString() {
        return "CyclicRegularClosing{"
                + "cycle=" + cycle
                + ", dayOfWeek=" + dayOfWeek
                + ", weekFields=" + weekFields
                + '}';
    }
}
