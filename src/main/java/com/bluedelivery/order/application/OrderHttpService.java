package com.bluedelivery.order.application;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItemList;

@Service
public class OrderHttpService implements OrderService {
    public Order takeOrder(Long userId, OrderItemList from) {
        return new Order();
    }
}
