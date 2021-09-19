package com.bluedelivery.notification;

import org.springframework.stereotype.Component;

import com.bluedelivery.common.event.Message;
import com.bluedelivery.notification.ThirdPartyNotifier.NotificationForm;
import com.bluedelivery.order.application.OrderCreatedEvent;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderDetails;
import com.bluedelivery.order.domain.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NotificationServer {
    
    private final ObjectMapper objectMapper;
    private final ThirdPartyNotifier thirdPartyNotifier;
    private final OrderRepository orderRepository;
    
    public void request(Message message) {
        NotificationForm form;
        try {
            OrderCreatedEvent event = objectMapper.readValue(message.getPayload(), OrderCreatedEvent.class);
            // TODO 주문정보(OrderDetails) 생성 (지금은 임시로 생성)
            Order order = orderRepository.getFlatOrderById(event.getOrderId()).orElseThrow();
            OrderDetails orderDetails = new OrderDetails(order.getOrderItems());
            form = new NotificationForm("token", orderDetails);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("fail deserialization " + e);
        }
        thirdPartyNotifier.request(form);
    }
}
