package com.delivery.restaurant.businesshour;


import java.time.LocalTime;
import java.util.Objects;

import com.delivery.utility.DayType;

public class BusinessHour {
    
    private LocalTime open;
    private LocalTime close;
    private DayType dayType;
    
    public BusinessHour() {
    }
    
    public BusinessHour(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
        this.dayType = DayType.DEFAULT;
    }
    
    public BusinessHour(LocalTime open, LocalTime close, DayType dayType) {
        this.open = open;
        this.close = close;
        this.dayType = dayType;
    }
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
    
    public DayType getDayType() {
        return dayType;
    }
    
    @Override
    public boolean equals(Object obj) {
        BusinessHour that = (BusinessHour) obj;
        return dayType == that.dayType;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(dayType);
    }
    
    @Override
    public String toString() {
        return "Business Hour : " + "day=" + dayType + " " + open + " ~ " + close;
    }
    
}
