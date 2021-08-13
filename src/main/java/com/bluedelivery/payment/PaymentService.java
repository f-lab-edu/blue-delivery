package com.bluedelivery.payment;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.domain.Order;

/**
 * 결제 서비스를 mock
 */
@Service
public class PaymentService {
    public Payment process(Order order) {
        Payment payment = new Payment();
        payment.pay();
        return payment;
    }
}
