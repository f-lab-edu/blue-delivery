package com.bluedelivery.order.application.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.common.EventEnvelope;
import com.bluedelivery.order.application.OrderCreatedEvent;
import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderRepository;
import com.bluedelivery.payment.Payment;
import com.bluedelivery.payment.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderHttpService implements OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentService paymentService;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Order takeOrder(Order.OrderForm form) {
        Order order = orderMapper.map(form);
        order.pay(paymentService.process(new Payment.PaymentForm(order)));
        orderRepository.save(order);
        publisher.publishEvent(EventEnvelope.builder()
                .aggregateId(order.getOrderId())
                .aggregateType(Order.class.getSimpleName())
                .event(OrderCreatedEvent.from(order))
                .eventType(OrderCreatedEvent.class.getSimpleName())
                .build());
        return order;
    }
    
}
