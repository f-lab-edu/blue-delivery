package com.delivery.shop.suspension;

import java.time.LocalDateTime;

public class Suspension {
    private LocalDateTime from;
    private LocalDateTime to;
    
    public Suspension() {
        this.from = LocalDateTime.now();
        this.to = from.plusNanos(0);
    }
    
    public Suspension(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }
    
    public boolean isSuspended(LocalDateTime now) {
        return now.isAfter(from) && now.isBefore(to);
    }
    
    public LocalDateTime getFrom() {
        return from;
    }
    
    public void setFrom(LocalDateTime from) {
        this.from = from;
    }
    
    public LocalDateTime getTo() {
        return to;
    }
    
    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
