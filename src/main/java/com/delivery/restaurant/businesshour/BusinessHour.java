package com.delivery.restaurant.businesshour;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class BusinessHour {
    
    private Long restId;
    private LocalTime open;
    private LocalTime close;
    private DayOfWeek dayOfWeek;
    
    public BusinessHour() {
    }
    
    public BusinessHour(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
    }
    
    public BusinessHour(Long restId, LocalTime open, LocalTime close) {
        this.restId = restId;
        this.open = open;
        this.close = close;
    }
    
    public BusinessHour(Long restId, LocalTime open, LocalTime close, DayOfWeek dayOfWeek) {
        this.restId = restId;
        this.open = open;
        this.close = close;
        this.dayOfWeek = dayOfWeek;
    }
    
    public void updateDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusinessHour that = (BusinessHour) obj;
        return Objects.equals(open, that.open) && Objects.equals(close, that.close);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(open, close);
    }
    
}
