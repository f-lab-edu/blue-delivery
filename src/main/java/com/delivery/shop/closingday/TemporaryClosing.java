package com.delivery.shop.closingday;

import java.time.LocalDate;

public class TemporaryClosing implements ClosingDay {
    
    private LocalDate from;
    private LocalDate to;
    
    public TemporaryClosing(LocalDate date) {
        this.from = date;
        this.to = date;
    }
    
    public TemporaryClosing(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) { // 실수로 from, to 를 반대로 입력한 경우
            this.from = to;
            this.to = from;
            return;
        }
        
        this.from = from;
        this.to = to;
        
    }
    
    @Override
    public boolean isClosedAt(LocalDate date) {
        return from.compareTo(date) <= 0 && to.compareTo(date) >= 0;
    }
}
