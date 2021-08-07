package com.bluedelivery.order.domain;

import static java.util.function.Predicate.*;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.order.interfaces.Cart;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OrderItem {
    private final Long menuId;
    private final String menuName;
    private final int price;
    private final int quantity;
    
    public OrderItem(Long menuId, String menuName, int price, int quantity) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
    }
    
    public static OrderItem from(Menu menu, int quantity) {
        return new OrderItem(menu.getId(), menu.getName(), menu.getPrice(), quantity);
    }
    
    public static OrderItem from(Cart.CartItem item) {
        return new OrderItem(item.getMenuId(), item.getName(), item.getPrice(), item.getQuantity());
    }
    
    public Long getMenuId() {
        return this.menuId;
    }
    
    public int getPrice() {
        return price;
    }
    
    public boolean validateWith(List<Menu> menus) {
        return menus.stream().anyMatch(not(this::isEqualTo));
    }
    
    private boolean isEqualTo(Menu menu) {
        return this.menuId == menu.getId()
                && this.menuName.equals(menu.getName())
                && this.price == menu.getPrice();
    }
}
