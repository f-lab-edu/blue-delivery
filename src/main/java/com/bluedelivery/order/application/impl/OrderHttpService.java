package com.bluedelivery.order.application.impl;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderRepository;
import com.bluedelivery.order.infra.OrderedEventHandler;
import com.bluedelivery.order.infra.OrderedEventHandler.OrderedNotificationEvent;
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
        Payment payment = paymentService.process(new Payment.PaymentForm(order));
        order.pay(payment);
        orderRepository.save(order);
        publisher.publishEvent(new OrderedNotificationEvent(order));
        return order;
    }

}
