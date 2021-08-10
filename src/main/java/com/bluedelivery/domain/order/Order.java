package com.bluedelivery.domain.order;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Builder;

public class Order {
    
    public enum OrderStatus {
        ACCEPT;
    }
    
    private OrderStatus orderStatus;
    private Long userId;
    private Long shopId;
    private final List<OrderItem> orderItems = new ArrayList<>();
    
    @Builder
    public Order(OrderStatus orderStatus,
                 Long userId, Long shopId, List<OrderItem> orderItems) {
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
