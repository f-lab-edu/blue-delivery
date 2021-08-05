package com.bluedelivery.domain.order;

import com.bluedelivery.domain.shop.Shop;

public class Order {
    
    public enum OrderStatus {
        WAIT
    }
    
    private OrderStatus orderStatus = OrderStatus.WAIT;
    private Shop shop;
    private OrderItemList orderItemList;
    
    Order() {
    
    }
    
    public Order(Shop shop) {
        this.shop = shop;
    }
    
    public Order(Shop shop, OrderItemList orderItemList) {
        this.shop = shop;
        this.orderItemList = orderItemList;
    }
    
    public void validate() {
        if (!shop.isOpeningAt()) {
            throw new IllegalStateException("Shop is not running now");
        }
        if (orderItemList.isEmpty()) {
            throw new IllegalArgumentException("Ordered list is Empty");
        }
    }
    
    public OrderStatus getStatus() {
        return this.orderStatus;
    }
}
