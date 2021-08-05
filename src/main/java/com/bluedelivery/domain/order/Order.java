package com.bluedelivery.domain.order;

import static com.bluedelivery.domain.order.ExceptionMessage.*;

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
        if (!shop.isOpen()) {
            throw new IllegalStateException(SHOP_IS_NOT_OPEN);
        }
        if (orderItemList.isEmpty()) {
            throw new IllegalArgumentException(ORDER_LIST_IS_EMPTY);
        }
        orderItemList.validateMenu();
        if (orderItemList.totalOrderAmount() < shop.getMinimumOrderAmount()) {
            throw new IllegalArgumentException(ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT);
        }
        
    }
    
    public OrderStatus getStatus() {
        return this.orderStatus;
    }
}
