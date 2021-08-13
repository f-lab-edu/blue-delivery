package com.bluedelivery.order.application.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.application.OrderValidator;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderRepository;
import com.bluedelivery.payment.Payment;
import com.bluedelivery.payment.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderHttpService implements OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final PaymentService paymentService;
    
    @Transactional
    public Order takeOrder(Order.OrderForm form) {
        Order order = form.createOrder(orderValidator);
        Payment payment = paymentService.process(order);
        order.pay(payment);
        orderRepository.save(order);
        return order;
    }
}
