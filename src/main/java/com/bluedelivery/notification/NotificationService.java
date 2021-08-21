package com.bluedelivery.notification;

import static com.bluedelivery.notification.ThirdPartyNotifier.*;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import com.bluedelivery.order.infra.OrderNotification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {
    
    private final ApplicationEventPublisher publisher;
    private final RedisTemplate<String, OrderNotification> redisTemplate;
    
    @Transactional
    public void notifyOrder() {
        redisTemplate.execute(requestNotification());
    }
    
    private SessionCallback<Object> requestNotification() {
        return new SessionCallback<>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi(); // transaction
                SetOperations ops = operations.opsForSet();
                Set<OrderNotification> events = ops.members(OrderNotification.key);
                if (!events.isEmpty()) {
                    send(ops, events);
                }
                return operations.exec();
            }
        };
    }
    
    private void send(SetOperations<String, OrderNotification> ops, Set<OrderNotification> events) {
        for (OrderNotification event : events) {
            log.info("제 3자 서비스로 전송 : " + event);
            publisher.publishEvent(new ACompanyNotificationEvent(event));
        }
        ops.remove(OrderNotification.key, events.toArray(new OrderNotification[0]));
    }
}
