package com.bluedelivery.order.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluedelivery.order.application.OrderValidator;
import com.bluedelivery.payment.Payment;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ORDERS")
public class Order {
    
    public enum OrderStatus {
        CREATED, PAYED, DONE;
    }
    
    @Id @GeneratedValue
    private Long orderId;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Long userId;
    private Long shopId;
    private Long paymentId;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID")
    private final List<OrderItem> orderItems = new ArrayList<>();
    
    protected Order() {
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
    
    public void pay(Payment payment) {
        if (payment.denied()) {
            throw new IllegalStateException("결제 실패");
        }
        this.orderStatus = OrderStatus.PAYED;
        this.paymentId = payment.id();
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
    
    @EqualsAndHashCode
    @Builder
    public static class OrderForm {
        private Long shopId;
        private Long userId;
        private List<OrderItem> orderItems;
        
        public Order createOrder(OrderValidator validator) {
            Order order = Order.builder()
                    .shopId(this.shopId)
                    .userId(this.userId)
                    .orderItems(this.orderItems)
                    .orderStatus(OrderStatus.CREATED)
                    .build();
            validator.validate(order);
            return order;
        }
    }
}
