package com.bluedelivery.order.application.impl;

import org.springframework.stereotype.Component;

import com.bluedelivery.order.application.OrderValidator;
import com.bluedelivery.order.domain.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderMapper {
    
    private final OrderValidator orderValidator;
    
    public Order map(Order.OrderForm form) {
        Order order = form.createOrder();
        orderValidator.validate(order);
        return order;
    }
}
