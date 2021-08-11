package com.bluedelivery;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuOption;
import com.bluedelivery.domain.menu.MenuOptionGroup;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;

public class OrderData {
    
    public static Menu.MenuBuilder menu() {
        return Menu.builder()
                .id(1L)
                .name("양념치킨")
                .composition("양념치킨과 치킨무 세트")
                .content("양념이지만 바삭바삭한 양념치킨입니다.")
                .isMain(true)
                .menuGroupId(1L)
                .price(15000)
                .status(Menu.MenuStatus.DEFAULT)
                .menuOptionGroup(List.of(
                                menuOptionGroup()
                        )
                );
    }
    
    public static MenuOptionGroup menuOptionGroup() {
        MenuOptionGroup menuOptionGroup = new MenuOptionGroup();
        menuOptionGroup.setId(1L);
        menuOptionGroup.setName("사이드메뉴");
        menuOptionGroup.setOptions(List.of(
                menuOption()
        ));
        return menuOptionGroup;
    }
    
    public static MenuOption menuOption() {
        MenuOption menuOption = new MenuOption();
        menuOption.setId(1L);
        menuOption.setName("치킨무");
        menuOption.setPrice(500);
        return menuOption;
    }
    
    public static Order.OrderBuilder order() {
        return Order.builder()
                .shopId(1L)
                .userId(1L)
                .orderItems(List.of(orderItem().build()));
    }
    
    public static OrderItem.OrderItemBuilder orderItem() {
        Menu chicken = menu().build();
        return OrderItem.builder()
                .menuName(chicken.getName())
                .menuId(chicken.getId())
                .price(chicken.getPrice())
                .quantity(1)
                .orderItemOptionGroups(List.of(
                                orderItemOptionGroup().build()
                        )
                );
    }
    
    public static OrderItem.OrderItemOptionGroup.OrderItemOptionGroupBuilder orderItemOptionGroup() {
        MenuOptionGroup menuOptionGroup = menuOptionGroup();
        return OrderItem.OrderItemOptionGroup.builder()
                .id(menuOptionGroup.getId())
                .name(menuOptionGroup.getName())
                .orderItemOptions(List.of(
                        orderItemOption().build()
                ));
    }
    
    public static OrderItem.OrderItemOption.OrderItemOptionBuilder orderItemOption() {
        MenuOption menuOption = menuOption();
        return OrderItem.OrderItemOption.builder()
                .id(menuOption.getId())
                .name(menuOption.getName())
                .price(menuOption.getPrice());
    }
}
