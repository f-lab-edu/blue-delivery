package com.bluedelivery.order.domain;

import static com.bluedelivery.order.domain.ExceptionMessage.*;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bluedelivery.domain.menu.Menu;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class OrderItemList {
    
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public OrderItemList(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public OrderItemList(OrderItem... orderItem) {
        this.orderItems = new ArrayList<>(Arrays.asList(orderItem));
    }
    
    public void validate(List<Menu> menus) {
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException(ORDER_LIST_IS_EMPTY);
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
