package com.bluedelivery.order.interfaces;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.order.domain.OrderItem;
import com.bluedelivery.order.domain.OrderItemList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long shopId;
    private List<CartItem> cartItems;
    
    public OrderItemList toOrderItemList() {
        return new OrderItemList(
                cartItems.stream()
                        .map(OrderItem::from)
                        .collect(toList()));
    }
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItem {
        private Long menuId;
        private String name;
        private int price;
        private int quantity;
        
        public static CartItem from(Menu menu, int quantity) {
            return new CartItem(menu.getId(), menu.getName(), menu.getPrice(), quantity);
        }
    }
}
