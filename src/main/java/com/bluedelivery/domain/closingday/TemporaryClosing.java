package com.bluedelivery.domain.closingday;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *  임시 휴무 정책
 */
@Entity
@DiscriminatorValue("TEMP")
public class TemporaryClosing extends ClosingPolicy {
    
    private LocalDate closeFrom;
    private LocalDate closeTo;
    
    public TemporaryClosing() {
    
    }
    
    public TemporaryClosing(LocalDate date) {
        this.closeFrom = date;
        this.closeTo = date;
    }
    
    public TemporaryClosing(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) { // to가 시간 순서상 앞서는 경우, 순서를 반대로 값을 저장한다.
            this.closeFrom = to;
            this.closeTo = from;
            return;
        }
        
        this.closeFrom = from;
        this.closeTo = to;
    }
    
    /**
     * 주어진 날짜가 지정된 임시휴무일 사이에 존재하는지 검사한다.
     * @param date 확인하고 싶은 날짜
     * @return 경계값을 포함한 휴무 시작/종료일 사이에 입력한 날짜가 존재하면 true
     */
    @Override
    public boolean isClosed(LocalDateTime date) {
        return closeFrom.compareTo(date.toLocalDate()) <= 0 && closeTo.compareTo(date.toLocalDate()) >= 0;
    }
    
    @Override
    public String toString() {
        return "TemporaryClosing{"
                + "from=" + closeFrom
                + ", to=" + closeTo
                + '}';
    }
}
