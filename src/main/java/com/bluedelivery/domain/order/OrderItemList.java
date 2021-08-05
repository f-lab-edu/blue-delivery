package com.bluedelivery.domain.order;

import static com.bluedelivery.domain.order.ExceptionMessage.ORDERED_AND_MENU_ARE_DIFFERENT;
import static com.bluedelivery.domain.order.ExceptionMessage.ORDERED_MENU_NOT_FOUND;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bluedelivery.domain.menu.Menu;

public class OrderItemList {
    
    private final List<OrderItem> orderItems;
    
    public OrderItemList(OrderItem... orderItem) {
        this.orderItems = new ArrayList<>(Arrays.asList(orderItem));
    }
    
    public boolean isEmpty() {
        return orderItems.isEmpty();
    }
    
    public void validateMenu(List<Menu> menus) {
        if (menus.size() != orderItems.size()) {
            throw new IllegalArgumentException(ORDERED_MENU_NOT_FOUND);
        }
        for (OrderItem orderItem : orderItems) {
            if (orderItem.validateWith(menus)) {
                throw new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT);
            }
        }
    }
    
    public int totalOrderAmount() {
        return orderItems.stream().mapToInt(x -> x.getPrice()).sum();
    }
    
    public Iterable<Long> getIds() {
        return orderItems.stream().map(x -> x.getMenuId()).collect(toList());
    }
}
