package com.bluedelivery.notification;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderNotificationScheduler {
    
    private final NotificationService notificationService;
    
    @Scheduled(fixedDelay = 1000L * 30, initialDelay = 1000L)
    public void lookupNotifications() {
        try {
            log.info("주문 알림을 전송합니다. ");
            notificationService.notifyOrder();
        } catch (RuntimeException exception) {
            log.error("주문 알림 전송 실패 : " + exception);
            throw new IllegalStateException("주문 알림 전송 실패 : " + exception);
        }
    }
}
