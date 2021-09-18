package com.bluedelivery.order.application;

import com.bluedelivery.order.domain.Order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreatedEvent {
    private Long orderId;
    private Long paymentId;
    private Long userId;
    private Long shopId;
    
    public static OrderCreatedEvent from(Order order) {
        return new OrderCreatedEvent(order.getOrderId(), order.getPaymentId(), order.getUserId(), order.getShopId());
    }
}
