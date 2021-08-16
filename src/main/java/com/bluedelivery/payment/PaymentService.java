package com.bluedelivery.payment;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 결제 서비스를 mock
 */
@Slf4j
@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    public Payment process(Payment.PaymentForm form) {
        Payment payment = form.createPayment();
        try {
            log.info("결제 요청.\n결제금액 : %d원\n결제방법 : %s", payment.paymentAmount(), payment.paymentMethod());
            payment.pay();
            paymentRepository.save(payment);
        } catch (RuntimeException ex) {
            payment.deny();
        }
        return payment;
    }
}
