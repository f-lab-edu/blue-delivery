package com.bluedelivery.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bluedelivery.common.event.Message;
import com.bluedelivery.common.event.MessageConsumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationKafkaConsumer implements MessageConsumer {
    
    private NotificationServer notificationServer;
    
    public NotificationKafkaConsumer(NotificationServer notificationServer) {
        this.notificationServer = notificationServer;
    }
    
    @Override
    @KafkaListener(topics = "order-status", groupId = "order-notification")
    public void consume(Message message) {
        log.info("reveiced message {}", message);
        notificationServer.request(message);
    }
}
