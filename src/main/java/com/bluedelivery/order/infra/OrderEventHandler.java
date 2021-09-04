package com.bluedelivery.order.infra;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bluedelivery.common.EventEnvelope;
import com.bluedelivery.common.Outbox;
import com.bluedelivery.common.OutboxRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderEventHandler {
    
    private final OutboxRepository outboxRepository;
    
    @EventListener
    public void saveOutbox(EventEnvelope event) {
        Outbox outbox = new Outbox(event.getAggregateId(), event.getAggregateType(), event.getEvent());
        outboxRepository.save(outbox);
    }
}
