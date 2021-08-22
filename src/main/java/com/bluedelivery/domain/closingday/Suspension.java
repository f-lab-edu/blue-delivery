package com.bluedelivery.domain.closingday;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("SUSPENSION")
public class Suspension extends ClosingPolicy {
    private LocalDateTime closeFrom;
    private LocalDateTime closeTo;
    
    // 정지기간을 정하지 않으면 from=to=현재시간 으로 만들어서 사실상 정지기간이 없도록 만듦
    public Suspension() {
        LocalDateTime now = LocalDateTime.now();
        this.closeFrom = now;
        this.closeTo = now;
    }
    
    public Suspension(LocalDateTime from, LocalDateTime closeTo) {
        this.closeFrom = from;
        this.closeTo = closeTo;
    }
    
    public boolean isClosed(LocalDateTime now) {
        return now.isAfter(closeFrom) && now.isBefore(closeTo);
    }
}
