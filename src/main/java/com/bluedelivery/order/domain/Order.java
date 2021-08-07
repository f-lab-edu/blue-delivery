package com.bluedelivery.order.domain;

import static com.bluedelivery.order.domain.ExceptionMessage.*;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.user.User;

import lombok.Builder;

public class Order {
    
    public enum OrderStatus {
        JUST_CREATED, BEFORE_PAYMENT, PAYED, DONE
    }
    
    private Long orderId;
    private OrderStatus orderStatus = OrderStatus.JUST_CREATED;
    private User user;
    private Shop shop;
    private OrderItemList orderItemList;
    private List<Menu> menus;
    
    public Order() {
    }
    
    @Builder
    public Order(Long orderId, OrderStatus orderStatus, User user, Shop shop,
                 OrderItemList orderItemList, List<Menu> menus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.user = user;
        this.shop = shop;
        this.orderItemList = orderItemList;
        this.menus = menus;
    }
    
    public void validate() {
        if (user == null) {
            throw new IllegalArgumentException(ORDER_USER_DOES_NOT_EXIST);
        }
        if (!shop.isOpen()) {
            throw new IllegalStateException(SHOP_IS_NOT_OPEN);
        }
        orderItemList.validate(menus);
        if (orderItemList.totalOrderAmount() < shop.getMinimumOrderAmount()) {
            throw new IllegalArgumentException(ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT);
        }
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public OrderStatus getStatus() {
        return this.orderStatus;
    }
}
