package com.bluedelivery.order.domain;

import static com.bluedelivery.order.domain.ExceptionMessage.*;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuRepository;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.user.User;

import lombok.Builder;

public class Order {
    
    public enum OrderStatus {
        ACCEPT
    }
    
    private Long orderId;
    private MenuRepository menuRepository;
    private OrderStatus orderStatus;
    private User user;
    private Shop shop;
    private OrderItemList orderItemList;
    
    public Order() {
    }
    
    @Builder
    public Order(Long orderId, MenuRepository menuRepository, OrderStatus orderStatus, User user, Shop shop,
                 OrderItemList orderItemList) {
        this.orderId = orderId;
        this.menuRepository = menuRepository;
        this.orderStatus = orderStatus;
        this.user = user;
        this.shop = shop;
        this.orderItemList = orderItemList;
    }
    
    public void validate() {
        if (user == null) {
            throw new IllegalArgumentException(ORDER_USER_DOES_NOT_EXIST);
        }
        if (!shop.isOpen()) {
            throw new IllegalStateException(SHOP_IS_NOT_OPEN);
        }
        if (orderItemList.isEmpty()) {
            throw new IllegalArgumentException(ORDER_LIST_IS_EMPTY);
        }
        orderItemList.validateMenu(getMenus());
        if (orderItemList.totalOrderAmount() < shop.getMinimumOrderAmount()) {
            throw new IllegalArgumentException(ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT);
        }
    }
    
    public void accept() {
        this.orderStatus = OrderStatus.ACCEPT;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public OrderStatus getStatus() {
        return this.orderStatus;
    }
    
    private List<Menu> getMenus() {
        return menuRepository.findAllById(orderItemList.getIds());
    }
}
