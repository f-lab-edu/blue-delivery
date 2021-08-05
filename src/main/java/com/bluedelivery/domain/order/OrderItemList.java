package com.bluedelivery.domain.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderItemList {
    
    private final List<OrderItem> orderItems;
    
    public OrderItemList(OrderItem... orderItem) {
        this.orderItems = new ArrayList<>(Arrays.asList(orderItem));
    }
    
    public boolean isEmpty() {
        return orderItems.isEmpty();
    }
    
}
