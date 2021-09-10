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
            System.out.printf("Partition: %d, Offset: %d", metadata.partition(), metadata.offset());
        } catch (RuntimeException exception) {
            return Optional.empty();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.of(outbox);
    }
    
}
