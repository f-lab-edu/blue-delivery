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
        if (from.isAfter(to)) { // to가 시간 순서상 앞서는 경우, 순서를 반대로 값을 저장한다.
            this.from = to;
            this.to = from;
            return;
        }
        
        this.from = from;
        this.to = to;
        
    }
    
    /**
     * 주어진 날짜가 지정된 임시휴무일 사이에 존재하는지 검사한다.
     * @param date 확인하고 싶은 날짜
     * @return 경계값을 포함한 휴무 시작/종료일 사이에 입력한 날짜가 존재하면 true
     */
    @Override
    public boolean isClosedAt(LocalDate date) {
        return from.compareTo(date) <= 0 && to.compareTo(date) >= 0;
    }
}
