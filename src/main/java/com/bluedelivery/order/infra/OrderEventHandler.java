package com.bluedelivery.order.infra;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bluedelivery.order.application.OrderOutbox;
import com.bluedelivery.order.application.OrderOutboxRepository;
import com.bluedelivery.order.domain.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderEventHandler {
    
    private final OrderOutboxRepository orderOutboxRepository;
    
    @EventListener
    public void saveOutbox(OrderCreatedEvent event) {
        OrderOutbox outbox = new OrderOutbox(event);
        orderOutboxRepository.save(outbox);
    }
}
