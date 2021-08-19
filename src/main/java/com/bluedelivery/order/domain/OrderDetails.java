package com.bluedelivery.order.domain;

import java.util.List;

import lombok.Getter;

// TODO 구현예정(주문상세조회)
@Getter
public class OrderDetails {
    private List<OrderItem> orderItems;
    public OrderDetails(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
