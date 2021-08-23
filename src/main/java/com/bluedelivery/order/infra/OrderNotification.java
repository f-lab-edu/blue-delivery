package com.bluedelivery.order.infra;

import java.util.List;

import com.bluedelivery.order.domain.OrderDetails;
import com.bluedelivery.order.domain.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class OrderNotification { // TODO 수정예정(주문상세조회 후)
    public static final String key = "notification:ordered";
    
    private Long orderId;
    private String username;
    private String userPhone;
    private List<OrderItem> orderItems;
    private int paymentAmount;
    
    public OrderNotification() {
    }
    
    @Builder
    public OrderNotification(Long orderId, String username, String userPhone,
                             List<OrderItem> orderItems, int paymentAmount) {
        this.orderId = orderId;
        this.username = username;
        this.userPhone = userPhone;
        this.orderItems = orderItems;
        this.paymentAmount = paymentAmount;
    }
    
    public static OrderNotification from(OrderDetails details) {
        return OrderNotification.builder()
                .orderId(1L)
                .username("username")
                .userPhone("01012341234")
                .orderItems(details.getOrderItems())
                .paymentAmount(10000)
                .build();
    }
    
    public String serializeJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
