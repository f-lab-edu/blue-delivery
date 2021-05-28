package com.delivery.restaurant;


import java.time.LocalTime;
import java.util.Objects;

import com.delivery.utility.DayType;

public class BusinessHour {
    
    private LocalTime open;
    private LocalTime close;
    private DayType day;
    
    public BusinessHour() {
    }
    
    public BusinessHour(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
        this.day = DayType.DEFAULT;
    }
    
    public BusinessHour(LocalTime open, LocalTime close, DayType day) {
        this.open = open;
        this.close = close;
        this.day = day;
    }
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
    
    public DayType getDay() {
        return day;
    }
    
    @Override
    public boolean equals(Object obj) {
        BusinessHour that = (BusinessHour) obj;
        return day == that.day;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(day);
    }
    
    @Override
    public String toString() {
        return "Business Hour : " + "day=" + day + " " + open + " ~ " + close;
    }
    
}
