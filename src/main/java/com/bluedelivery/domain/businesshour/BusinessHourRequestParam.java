package com.bluedelivery.domain.businesshour;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class BusinessHourRequestParam {
    
    private LocalTime open;
    private LocalTime close;
    
    public BusinessHourRequestParam() {
    }
    
    public BusinessHourRequestParam(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
    }
    
    public BusinessHour toEntity(Long shopId, DayOfWeek dayOfWeek) {
        return new BusinessHour(shopId, open, close, dayOfWeek);
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
