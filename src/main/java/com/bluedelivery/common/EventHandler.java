package com.bluedelivery.common;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EventHandler {
    
    private final OutboxRepository outboxRepository;
    
    @EventListener
    public void saveOutbox(EventEnvelope event) {
        Outbox outbox = new Outbox(
                event.getAggregateId(), event.getAggregateType(), event.getEvent(), event.getEventType());
        outboxRepository.save(outbox);
    }
}
