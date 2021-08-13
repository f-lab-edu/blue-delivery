package com.bluedelivery.order.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Builder;

public class Order {
    
    public enum OrderStatus {
        ACCEPT;
    }
    
    private Long orderId;
    private OrderStatus orderStatus;
    private Long userId;
    private Long shopId;
    private final List<OrderItem> orderItems = new ArrayList<>();
    
    public Order() {
    }
    
    @Builder
    public Order(Long orderId, OrderStatus orderStatus,
                 Long userId, Long shopId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.shopId = shopId;
        this.orderItems.addAll(orderItems);
    }
    
    public int numberOfOrderItems() {
        return orderItems.size();
    }
    
    public List<Long> getOrderItemIds() {
        return orderItems.stream().map(OrderItem::getMenuId).collect(toList());
    }
    
    
    public int totalOrderAmount() {
        return orderItems.stream().mapToInt(item -> item.totalOrderAmount()).sum();
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public OrderStatus getStatus() {
        return this.orderStatus;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public Long getShopId() {
        return shopId;
    }
    
    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
