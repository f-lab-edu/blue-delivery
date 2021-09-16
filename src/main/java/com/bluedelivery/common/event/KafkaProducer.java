package com.bluedelivery.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer implements MessageProducer {
    
    private final KafkaTemplate<String, Message> kafkaTemplate;
    
    @Override
    public List<Outbox> produce(List<Outbox> outboxes) {
        List<Outbox> successes = new ArrayList<>();
        for (Outbox outbox : outboxes) {
            insert(outbox).ifPresent(success -> successes.add(success));
        }
        return successes;
    }
    
    private Optional<Outbox> insert(Outbox outbox) {
        Map<String, Object> header = new HashMap<>();
        Long eventId = outbox.getOutboxId();
        String eventType = outbox.getEventType();
        
        header.put("eventId", eventId);
        header.put("eventType", eventType);
        Message message = new Message(header, outbox.getPayload());
        
        try {
            RecordMetadata metadata = kafkaTemplate.send(eventType, message).get().getRecordMetadata();
            log.debug("Partition: {}, Offset: {}", metadata.partition(), metadata.offset());
        } catch (RuntimeException | InterruptedException | ExecutionException exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
        return Optional.of(outbox);
    }
    
}
