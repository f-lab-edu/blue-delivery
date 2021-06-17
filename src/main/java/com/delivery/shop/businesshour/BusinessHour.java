package com.delivery.shop.businesshour;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class BusinessHour {
    
    private Long shopId;
    private LocalTime open;
    private LocalTime close;
    private DayOfWeek dayOfWeek;
    
    public BusinessHour() {
    }
    
    public BusinessHour(DayOfWeek dayOfWeek, LocalTime open, LocalTime close) {
        this.dayOfWeek = dayOfWeek;
        this.open = open;
        this.close = close;
    }
    
    public BusinessHour(Long shopId, LocalTime open, LocalTime close, DayOfWeek dayOfWeek) {
        this.shopId = shopId;
        this.open = open;
        this.close = close;
        this.dayOfWeek = dayOfWeek;
    }
    
    public Long getShopId() {
        return shopId;
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
    
    public boolean isBetween(LocalTime date) {
        return open.compareTo(date) <= 0
                && close.compareTo(date) >= 0;
    }
}
