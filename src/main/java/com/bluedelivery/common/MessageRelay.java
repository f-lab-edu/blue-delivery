package com.bluedelivery.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageRelay {
    
    @Scheduled(fixedDelay = 1000L * 3, initialDelay = 1000L)
    @LockTask(task = "ordered_notifications", expireAfter = 1000L)
    public void notifyOrderEvent() {
        try {
            log.info("주문 알림을 전송합니다. ");
        } catch (RuntimeException exception) {
            log.error("주문 알림 전송 실패 : " + exception);
            throw new IllegalStateException("주문 알림 전송 실패 : " + exception);
        }
    }
}
