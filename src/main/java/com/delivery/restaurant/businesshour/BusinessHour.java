package com.delivery.restaurant.businesshour;


import java.time.LocalTime;
import java.util.Objects;

public class BusinessHour {
    
    private LocalTime open;
    private LocalTime close;
    
    public BusinessHour() {
    }
    
    public BusinessHour(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
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
