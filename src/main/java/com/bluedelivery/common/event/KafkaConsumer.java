package com.bluedelivery.common.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bluedelivery.notification.NotificationServer;

@Component
public class KafkaConsumer implements MessageConsumer {
    
    private NotificationServer notificationServer;
    
    public KafkaConsumer(NotificationServer notificationServer) {
        this.notificationServer = notificationServer;
    }
    
    @Override
    @KafkaListener(topics = "OrderCreatedEvent", groupId = "OrderCreatedEvent")
    public void consume(Message message) {
        notificationServer.request(message);
    }
}
