package com.bluedelivery;

import java.util.List;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuOption;
import com.bluedelivery.domain.menu.MenuOptionGroup;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;
import com.bluedelivery.order.interfaces.Cart;
import com.bluedelivery.order.interfaces.Cart.CartItemOption.CartItemOptionBuilder;
import com.bluedelivery.order.interfaces.Cart.CartItemOptionGroup.CartItemOptionGroupBuilder;

public class OrderData {
    private static final Long SHOP_ID = 1L;
    private static final Long VALID_USER_ID = 1L;
    private static Long menuId = 1L;
    private static String menuName = "양념치킨";
    private static String menuComposition = "양념치킨과 치킨무 세트";
    private static String menuContent = "양념치킨과 치킨무 세트";
    private static int menuPrice = 15000;
    private static Long optionGroupId = 1L;
    private static String optionGroupName = "사이드메뉴";
    private static Long optionId = 1L;
    private static String optionName = "치킨무";
    private static int optionPrice = 500;
    
    public static Order.OrderForm.OrderFormBuilder orderForm() {
        return Order.OrderForm.builder()
                .shopId(SHOP_ID)
                .userId(VALID_USER_ID)
                .orderItems(List.of(OrderItem.from(cartItem().build())));
    }
    
    public static Menu.MenuBuilder menu() {
        return Menu.builder()
                .id(menuId)
                .name(menuName)
                .composition(menuComposition)
                .content(menuContent)
                .isMain(true)
                .menuGroupId(1L)
                .price(menuPrice)
                .status(Menu.MenuStatus.DEFAULT)
                .menuOptionGroup(List.of(
                                menuOptionGroup()
                        )
                );
    }
    
    public static MenuOptionGroup menuOptionGroup() {
        MenuOptionGroup menuOptionGroup = new MenuOptionGroup();
        menuOptionGroup.setId(optionGroupId);
        menuOptionGroup.setName(optionGroupName);
        menuOptionGroup.setOptions(List.of(
                menuOption()
        ));
        return menuOptionGroup;
    }
    
    public static MenuOption menuOption() {
        MenuOption menuOption = new MenuOption();
        menuOption.setId(optionId);
        menuOption.setName(optionName);
        menuOption.setPrice(optionPrice);
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
    
    public static Cart.CartItem.CartItemBuilder cartItem() {
        return Cart.CartItem.builder()
                .menuId(menuId)
                .name(menuName)
                .price(menuPrice)
                .cartItemOptionGroups(List.of(cartOptionGroup().build()));
    }
    
    public static CartItemOptionGroupBuilder cartOptionGroup() {
        return Cart.CartItemOptionGroup.builder()
                .id(optionGroupId)
                .name(optionGroupName)
                .orderItemOptions(List.of(cartItemOption().build()));
    }
    
    public static CartItemOptionBuilder cartItemOption() {
        return Cart.CartItemOption.builder()
                .id(optionId)
                .name(optionName)
                .price(optionPrice);
    }
    
}
