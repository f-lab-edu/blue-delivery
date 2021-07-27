package com.bluedelivery.application.shop;

import java.time.DayOfWeek;

import com.bluedelivery.shop.closingday.CyclicRegularClosing;
import com.bluedelivery.shop.closingday.RegularClosingDay;
import com.bluedelivery.shop.closingday.WeeklyRegularClosing;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegularClosingParam {
    
    private CyclicRegularClosing.Cycle cycle;
    private DayOfWeek dayOfWeek;
    
    public RegularClosingParam(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public RegularClosingParam(CyclicRegularClosing.Cycle cycle, DayOfWeek dayOfWeek) {
        this.cycle = cycle;
        this.dayOfWeek = dayOfWeek;
    }
    
    public RegularClosingDay toEntity() {
        if (this.cycle == null) {
            return new WeeklyRegularClosing(this.dayOfWeek);
        }
        return new CyclicRegularClosing(this.cycle, dayOfWeek);
    }
    
    public CyclicRegularClosing.Cycle getCycle() {
        return cycle;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    
    @Override
    public String toString() {
        return "RegularClosingParam{"
                + "cycle=" + cycle
                + ", dayOfWeek=" + dayOfWeek
                + '}';
    }
}
