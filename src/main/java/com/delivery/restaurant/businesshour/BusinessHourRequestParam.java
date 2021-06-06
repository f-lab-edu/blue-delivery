package com.delivery.restaurant.businesshour;


import java.time.DayOfWeek;
import java.time.LocalTime;

import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.DayType;

public class BusinessHourRequestParam {
    
    private LocalTime open;
    private LocalTime close;
    
    public BusinessHourRequestParam() {
    }
    
    public BusinessHourRequestParam(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
    }
    
    public BusinessHour toEntity(DayOfWeek dayOfWeek) {
        return new BusinessHour(dayOfWeek, open, close);
    }
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
    
    public void setOpen(LocalTime open) {
        this.open = open;
    }
    
    public void setClose(LocalTime close) {
        this.close = close;
    }
    
}
