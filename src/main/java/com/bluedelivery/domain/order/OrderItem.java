package com.bluedelivery.domain.order;

import static java.util.function.Predicate.*;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;

public class OrderItem {
    private final Long menuId;
    private final String menuName;
    private final int price;
    
    public OrderItem(Long menuId, String menuName, int price) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
    }
    
    public static OrderItem from(Menu menu) {
        return new OrderItem(menu.getId(), menu.getName(), menu.getPrice());
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
