package com.bluedelivery.order.application;

import com.bluedelivery.order.domain.Order;

public interface OrderService {
    Order takeOrder(Order.OrderForm form);
}
