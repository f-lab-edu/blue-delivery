package com.bluedelivery.order.application;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderDetails;
import com.bluedelivery.order.domain.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchOrderService {
    
    private final OrderRepository orderRepository;
    
    // TODO 구현예정(주문상세조회)
    public OrderDetails getOrderDetails(Long orderId) {
        Order order = orderRepository.getFlatOrderById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("empty order"));
        OrderDetails details = new OrderDetails(order.getOrderItems());
        return details;
    }
}
