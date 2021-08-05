package com.bluedelivery.domain.order;

import static com.bluedelivery.domain.order.ExceptionMessage.*;

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
    
    private MenuRepository menuRepository;
    private OrderStatus orderStatus;
    private User user;
    private Shop shop;
    private OrderItemList orderItemList;
    
    @Builder
    public Order(MenuRepository menuRepository, OrderStatus orderStatus,
                 User user, Shop shop, OrderItemList orderItemList) {
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
    
    public OrderStatus getStatus() {
        return this.orderStatus;
    }
    
    private List<Menu> getMenus() {
        return menuRepository.findAllById(orderItemList.getIds());
    }
}
