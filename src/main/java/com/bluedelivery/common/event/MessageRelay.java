package com.bluedelivery.common.event;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bluedelivery.common.shedlock.LockTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageRelay {

    private final MessageProducer messageProducer;
    private final OutboxRepository outboxRepository;
    
    @Scheduled(fixedDelay = 1000L * 3, initialDelay = 1000L)
    @LockTask(task = "ordered_notifications", expireAfter = 1000L)
    public void notifyOrderEvent() {
        try {
            log.info("주문 알림을 전송합니다. ");
            List<Outbox> outboxes = outboxRepository.findAll();
            List<Outbox> successes = messageProducer.produce(outboxes);
            outboxRepository.deleteAll(successes);
        } catch (RuntimeException exception) {
            log.error("주문 알림 전송 실패 : " + exception);
            throw new IllegalStateException("주문 알림 전송 실패 : " + exception);
        }
    }
}
