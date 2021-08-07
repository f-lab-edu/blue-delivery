package com.bluedelivery.order.application;

import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItemList;

public interface OrderService {
    Order takeOrder(Long userId, OrderItemList from);
}
