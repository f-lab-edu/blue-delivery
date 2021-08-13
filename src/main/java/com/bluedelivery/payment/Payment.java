package com.bluedelivery.payment;

import static com.bluedelivery.payment.Payment.PaymentStatus.DENIED;
import static com.bluedelivery.payment.Payment.PaymentStatus.PAYED;

/**
 * payment service mock을 위해 만든 클래스
 */
public class Payment {
    
    public enum PaymentStatus {
        DENIED, HOLD, PAYED;
    }
    
    private Long paymentId;
    private PaymentStatus status;
    
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
