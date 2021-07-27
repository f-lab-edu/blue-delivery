package com.bluedelivery.application.shop;

import java.time.LocalDate;

import com.bluedelivery.shop.closingday.TemporaryClosing;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TemporaryClosingParam {
    
    private LocalDate from;
    private LocalDate to;
    
    public TemporaryClosingParam(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) { // to가 시간 순서상 앞서는 경우, 순서를 반대로 값을 저장한다.
            this.from = to;
            this.to = from;
            return;
        }
    
        this.from = from;
        this.to = to;
    }
    
    public TemporaryClosing toEntity() {
        return new TemporaryClosing(this.from, this.to);
    }
    
    public LocalDate getFrom() {
        return from;
    }
    
    public LocalDate getTo() {
        return to;
    }
}
