package com.delivery.restaurant.businesshour;


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
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
}
