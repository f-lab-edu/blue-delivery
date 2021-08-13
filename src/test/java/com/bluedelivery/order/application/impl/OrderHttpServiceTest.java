package com.bluedelivery.order.application.impl;

import org.junit.jupiter.api.BeforeEach;

import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.application.OrderValidator;
import com.bluedelivery.order.domain.OrderRepository;
import com.bluedelivery.payment.PaymentService;

class OrderHttpServiceTest {
    
    private OrderService orderService;
    private OrderRepository orderRepository;
    private OrderValidator orderValidator;
    private PaymentService paymentService;
    
    @BeforeEach
    void setup() {
        orderService = new OrderHttpService(orderRepository, orderValidator, paymentService);
    }
    
}
