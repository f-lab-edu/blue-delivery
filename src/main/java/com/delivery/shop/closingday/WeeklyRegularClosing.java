package com.delivery.shop.closingday;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyRegularClosing extends RegularClosingDay {
    
    private DayOfWeek dayOfWeek;
    
    public WeeklyRegularClosing(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public boolean isClosedAt(LocalDate localDate) {
        return this.dayOfWeek == localDate.getDayOfWeek();
    }
}
