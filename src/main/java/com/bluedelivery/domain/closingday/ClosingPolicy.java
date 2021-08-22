package com.bluedelivery.domain.closingday;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class ClosingPolicy {
    @Id @GeneratedValue
    @Column(name = "CLOSING_POLICY_ID")
    private Long id;
    
    /**
     * 주어진 날짜가 지정한 휴무일과 일치하는지 확인한다.
     * @param date 확인하고 싶은 날짜
     * @return 휴무일에 해당한다면 true
     */
    public abstract boolean isClosed(LocalDateTime date);
}

