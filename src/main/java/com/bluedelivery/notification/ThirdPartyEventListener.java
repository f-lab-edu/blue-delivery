package com.bluedelivery.notification;

import javax.transaction.Transactional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.bluedelivery.order.infra.OrderNotification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ThirdPartyEventListener {
    
    private final ThirdPartyNotifier thirdPartyNotifier;
    private final RedisTemplate<String, OrderNotification> redisTemplate;
    
    // NotificationService -> 제3서비스가 하나로 묶이면 실패하는 알림이 생기면 전체 알림이 취소될 수 있다.
    // 그래서 이벤트를 발행해서 각각 처리될 수 있도록 하고 실패한 알림은 다시 redis에 저장한다.
    @Async
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void request(ThirdPartyNotifier.ACompanyNotificationEvent form) {
        boolean succeed = thirdPartyNotifier.request(form);
        if (!succeed) {
            redisTemplate.opsForSet().add(OrderNotification.key, form.getOrderNotification());
        }
    }
}
