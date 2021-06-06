package com.delivery.restaurant.businesshour;


import java.time.DayOfWeek;
import java.time.LocalTime;

import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.DayType;

public class BusinessHourRequestParam {
    
    private DayType dayType;
    private LocalTime open;
    private LocalTime close;
    
    public BusinessHourRequestParam() {
    }
    
    public BusinessHourRequestParam(DayType dayType, LocalTime open, LocalTime close) {
        this.dayType = dayType;
        this.open = open;
        this.close = close;
    }
    
    public boolean is(DayType dayType) {
        return this.dayType == dayType;
    }
    
    public BusinessHour toEntity(DayOfWeek dayOfWeek) {
        return new BusinessHour(dayOfWeek, open, close);
    }
    
    public DayType getDayType() {
        return dayType;
    }
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
    
    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }
    
    public void setOpen(LocalTime open) {
        this.open = open;
    }
    
    public void setClose(LocalTime close) {
        this.close = close;
    }
    
}
