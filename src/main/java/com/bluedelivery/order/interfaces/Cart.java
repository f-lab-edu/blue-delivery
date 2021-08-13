package com.bluedelivery.order.interfaces;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long shopId;
    private List<CartItem> cartItems;
    
    public Order.OrderForm toOrderForm(Long userId) {
        return Order.OrderForm.builder()
                .userId(userId)
                .shopId(this.shopId)
                .orderItems(
                        cartItems.stream()
                                .map(OrderItem::from)
                                .collect(toList()))
                .build();
    }
    
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItem {
        private Long menuId;
        private String name;
        private int price;
        private int quantity;
        private List<CartItemOptionGroup> cartItemOptionGroups;
    }
    
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemOptionGroup {
        private Long id;
        private String name;
        private List<CartItemOption> orderItemOptions;
    }
    
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemOption {
        private Long id;
        private String name;
        private int price;
    }
}
