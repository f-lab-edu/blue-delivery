package com.bluedelivery.domain.closingday;

import java.time.LocalDateTime;

public class Suspension extends ClosingPolicy {
    private LocalDateTime from;
    private LocalDateTime to;
    
    // 정지기간을 정하지 않으면 from=to=현재시간 으로 만들어서 사실상 정지기간이 없도록 만듦
    public Suspension() {
        LocalDateTime now = LocalDateTime.now();
        this.from = now;
        this.to = now;
    }
    
    public Suspension(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }
    
    public boolean isClosed(LocalDateTime now) {
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
