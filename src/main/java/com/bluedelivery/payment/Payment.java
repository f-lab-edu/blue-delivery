package com.bluedelivery.payment;

import static com.bluedelivery.payment.Payment.PaymentStatus.*;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bluedelivery.order.domain.Order;

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
    
    public enum PaymentMethod {
        CREDIT_CARD, CASH, OFFLINE_CREDIT_CARD
    }
    
    @Id @GeneratedValue
    private Long paymentId;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    private Long orderId;
    private int paymentAmount;
    
    private Payment(PaymentMethod paymentMethod, Long orderId, int paymentAmount) {
        this.status = CREATED;
        this.paymentMethod = paymentMethod;
        this.orderId = orderId;
        this.paymentAmount = paymentAmount;
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
    
    public Long orderId() {
        return this.orderId;
    }
    
    public int paymentAmount() {
        return this.paymentAmount;
    }
    
    public PaymentMethod paymentMethod() {
        return this.paymentMethod;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Payment payment = (Payment) obj;
        return Objects.equals(paymentId, payment.paymentId) && Objects.equals(orderId, payment.orderId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(paymentId, orderId);
    }
    
    @EqualsAndHashCode
    public static class PaymentForm {
        private Order order;
        private PaymentMethod paymentMethod;
    
        public PaymentForm(Order order) {
            this.order = order;
            this.paymentMethod = PaymentMethod.CREDIT_CARD; // mock 이라 임의로 지정
        }
        
        public Payment createPayment() {
            return new Payment(this.paymentMethod, order.getOrderId(), order.totalOrderAmount());
        }
    }
}
