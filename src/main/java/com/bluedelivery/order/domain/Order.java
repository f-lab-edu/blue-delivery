package com.bluedelivery.order.domain;

import static com.bluedelivery.order.domain.ExceptionMessage.ORDERED_AND_MENU_ARE_DIFFERENT;
import static com.bluedelivery.order.domain.ExceptionMessage.ORDER_LIST_IS_EMPTY;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.payment.Payment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table(name = "ORDERS")
public class Order {
    
    public enum OrderStatus {
        CREATED, PAYED, ACCEPTED, IN_DELIVERY, DELIVERED;
    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Long userId;
    private Long shopId;
    private Long paymentId;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
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
        orderItems.forEach(item -> item.setOrder(this));
        this.orderItems.addAll(orderItems);
    }
    
    public void pay(Payment payment) {
        if (payment.isDenied()) {
            throw new IllegalStateException("결제 실패");
        }
        this.orderStatus = OrderStatus.PAYED;
        this.paymentId = payment.id();
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
    
    public Long getPaymentId() {
        return paymentId;
    }
    
    public void isValidMenu(List<Menu> menus) {
        if (orderItems.size() == 0) {
            throw new IllegalArgumentException(ORDER_LIST_IS_EMPTY);
        }
        for (OrderItem orderItem : this.orderItems) {
            menus.stream()
                    .filter(menu -> menu.getId() == orderItem.getMenuId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT))
                    .validate(orderItem);
        }
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return Objects.equals(orderId, order.orderId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
    
    @EqualsAndHashCode
    @Builder
    @Getter
    public static class OrderForm {
        private Long shopId;
        private Long userId;
        private List<OrderItem> orderItems;
        
        public Order createOrder() {
            Order order = Order.builder()
                    .shopId(this.shopId)
                    .userId(this.userId)
                    .orderItems(this.orderItems)
                    .orderStatus(OrderStatus.CREATED)
                    .build();
            return order;
        }
    }
}
