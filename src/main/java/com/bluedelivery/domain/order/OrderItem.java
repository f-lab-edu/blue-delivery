package com.bluedelivery.domain.order;

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
    
    public Long getMenuId() {
        return this.menuId;
    }
    
    public boolean validate(Menu menu) {
        return this.menuId == menu.getId()
                && this.menuName.equals(menu.getName())
                && this.price == menu.getPrice();
    }
}
