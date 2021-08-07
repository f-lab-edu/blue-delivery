package com.bluedelivery;

import static com.bluedelivery.order.domain.Order.OrderStatus.BEFORE_PAYMENT;
import static com.bluedelivery.order.domain.Order.OrderStatus.JUST_CREATED;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.user.User;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;
import com.bluedelivery.order.domain.OrderItemList;

public class Data {
    public static Menu.MenuBuilder chicken() {
        return Menu.builder()
                .id(1L)
                .name("양념치킨")
                .composition("양념치킨과 치킨무 세트")
                .content("양념이지만 바삭바삭한 양념치킨입니다.")
                .isMain(true)
                .menuGroupId(1L)
                .price(15000)
                .status(Menu.MenuStatus.DEFAULT);
    }
    
    public static Order.OrderBuilder order() {
        return Order.builder()
                .user(new User())
                .shop(new Shop())
                .orderItemList(new OrderItemList(OrderItem.from(chicken().build(), 1)))
                .menus(List.of(chicken().build()));
    }
    
}
