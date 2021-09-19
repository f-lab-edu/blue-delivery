package com.bluedelivery.order.infra;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bluedelivery.common.event.MessageProducer;
import com.bluedelivery.common.event.MessageRelay;
import com.bluedelivery.common.event.Outbox;
import com.bluedelivery.common.event.OutboxRepository;
import com.bluedelivery.common.shedlock.LockTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderMessageRelay implements MessageRelay {

    private final MessageProducer messageProducer;
    private final OutboxRepository outboxRepository;
    
    @Override
    @Scheduled(fixedDelay = 1000L * 3, initialDelay = 1000L)
    @LockTask(task = "publish-event", expireAfter = 1000L)
    public void publishEvent() {
        try {
            List<Outbox> outboxes = outboxRepository.findAll(Sort.by("createdDate"));
            List<Outbox> successes = messageProducer.produce("order-status", outboxes);
            outboxRepository.deleteAll(successes);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("이벤트 전달 실패 : " + exception);
        }
    }
}
