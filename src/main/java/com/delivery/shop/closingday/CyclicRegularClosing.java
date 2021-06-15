package com.delivery.shop.closingday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

public class CyclicRegularClosing extends RegularClosingDay {
    
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
    
    private Cycle cycle;
    private DayOfWeek dayOfWeek;
    private TemporalField weekFields; // 외부에서 입력받는 LocalDate 의 주(첫째주, 둘째주...)를 계산하기 위한 기준이 됌
    
    public CyclicRegularClosing(Cycle cycle, DayOfWeek dayOfWeek) {
        this.cycle = cycle;
        this.dayOfWeek = dayOfWeek;
        this.weekFields = WeekFields.ISO.weekOfMonth();
    }
    
    // TODO 코드 다시 확인
    @Override
    public boolean isClosedAt(LocalDate localDate) {
        // 요일 확인
        if (localDate.getDayOfWeek() != this.dayOfWeek) {
            return false;
        }
        // 주기 확인
        if (this.cycle == Cycle.LAST) {
            if (isLastWeek(localDate)) {
                return true;
            }
            return false;
        }
        if (getNumberOfWeek(localDate) == this.cycle.getCycleAsInt()) {
            return true;
        }
        return false;
    }
    
    // 입력받은 LocalDate의 주 == 입력받은 LocalDate의 Month의 마지막 주인지 확인
    // 월을 시작하는 주의 일수가 4일 미만이면 전 달의 마지막주로 취급하고 값은 0이 된다. 그러므로 'last'와 '0'을 검사한다.
    // (ex. 2021/08/01은 일요일이고 그 주의 유일한 날이다. (4일 미만이므로 7월의 마지막 주로 간주한다.)
    private boolean isLastWeek(LocalDate localDate) {
        YearMonth currentYearMonth = YearMonth.from(localDate);
        int numberOfWeek = getNumberOfWeek(localDate);
        int last = getNumberOfWeek(currentYearMonth.atEndOfMonth());
        if (numberOfWeek == last || numberOfWeek == 0) {
            return true;
        }
        return false;
    }
    
    private int getNumberOfWeek(LocalDate localDate) {
        return localDate.get(weekFields);
    }
}
