package com.bluedelivery.notification;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.infra.OrderNotification;
import com.bluedelivery.order.infra.OrderNotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {
    
    private final OrderNotificationRepository orderNotificationRepository;
    
    public void notifyOrder() {
        Iterable<OrderNotification> events = orderNotificationRepository.findAll();
        for (OrderNotification event : events) {
            log.info("제 3자 서비스로 전송 : " + event);
        }
        orderNotificationRepository.deleteAll(events);
    }
}
