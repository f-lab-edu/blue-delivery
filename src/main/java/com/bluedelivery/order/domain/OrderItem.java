package com.bluedelivery.order.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bluedelivery.order.interfaces.Cart;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
public class OrderItem {
    private final Long menuId;
    private final String menuName;
    private final int price;
    private final int quantity;
    private final List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();
    
    @Builder
    public OrderItem(Long menuId, String menuName, int price, int quantity,
                     List<OrderItemOptionGroup> orderItemOptionGroups) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
        this.orderItemOptionGroups.addAll(orderItemOptionGroups);
    }
    
    public Long getMenuId() {
        return this.menuId;
    }
    
    public String getMenuName() {
        return menuName;
    }
    
    public int getPrice() {
        return price;
    }
    
    public List<OrderItemOptionGroup> getOrderItemOptionGroups() {
        return Collections.unmodifiableList(orderItemOptionGroups);
    }
    
    public int totalOrderAmount() {
        return (price * quantity) + orderItemOptionGroups.stream().mapToInt(group -> group.totalOrderAmount()).sum();
    }
    
    public static OrderItem from(Cart.CartItem cartItem) {
        return OrderItem.builder()
                .menuId(cartItem.getMenuId())
                .menuName(cartItem.getName())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .orderItemOptionGroups(Collections.emptyList()) // TODO
                .build();
    }
    
    @Getter
    public static class OrderItemOptionGroup {
        
        private final Long id;
        private final String name;
        private final List<OrderItemOption> orderItemOptions = new ArrayList<>();
    
        @Builder
        public OrderItemOptionGroup(Long id, String name, List<OrderItemOption> orderItemOptions) {
            this.id = id;
            this.name = name;
            this.orderItemOptions.addAll(orderItemOptions);
        }
    
        public int totalOrderAmount() {
            return orderItemOptions.stream().mapToInt(option -> option.getPrice()).sum();
        }
    }
    
    @Builder
    @Getter
    @RequiredArgsConstructor
    public static class OrderItemOption {
        private final Long id;
        private final String name;
        private final int price;
    }
}
