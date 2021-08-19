package com.bluedelivery.order.infra;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.bluedelivery.domain.address.Address;
import com.bluedelivery.order.domain.OrderDetails;
import com.bluedelivery.order.domain.OrderItem;

import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash("notification:ordered")
public class OrderNotification {
    @Id
    private Long orderId;
    private String username;
    private String userPhone;
    private Address address;
    private List<OrderItem> orderItems;
    private int paymentAmount;
    
    @Builder
    public OrderNotification(Long orderId, String username, String userPhone, Address address,
                             List<OrderItem> orderItems, int paymentAmount) {
        this.orderId = orderId;
        this.username = username;
        this.userPhone = userPhone;
        this.address = address;
        this.orderItems = orderItems;
        this.paymentAmount = paymentAmount;
    }
    
    // TODO 수정예정(주문상세조회 후)
    public static OrderNotification from(OrderDetails details) {
        return OrderNotification.builder()
                .orderId(1L)
                .username("username")
                .userPhone("01012341234")
                .address(new Address())
                .orderItems(details.getOrderItems())
                .paymentAmount(10000)
                .build();
    }
}
