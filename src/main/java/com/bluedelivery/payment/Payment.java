package com.bluedelivery.payment;

import static com.bluedelivery.payment.Payment.PaymentStatus.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * payment service mock을 위해 만든 클래스
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Payment {
    
    public enum PaymentStatus {
        CREATED, DENIED, HOLD, PAYED;
    }
    
    @Id @GeneratedValue
    private Long paymentId;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    private Payment(PaymentStatus status) {
        this.status = status;
    }
    
    public static Payment create() {
        return new Payment(CREATED);
    }
    
    public Long id() {
        return paymentId;
    }
    
    public void deny() {
        this.status = DENIED;
    }
    
    public void pay() {
        this.status = PAYED;
    }
    
    public boolean denied() {
        return status == DENIED;
    }
}
