package com.bluedelivery.order.application;

import java.util.List;

import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;

public interface OrderService {
    Order takeOrder(Long userId, List<OrderItem> orderItemList);
}
