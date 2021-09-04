package com.bluedelivery.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long paymentId;
    private Long userId;
    private Long shopId;
}
