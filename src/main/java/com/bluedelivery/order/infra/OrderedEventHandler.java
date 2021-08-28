package com.bluedelivery.order.infra;

import javax.transaction.Transactional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.bluedelivery.notification.Notification;
import com.bluedelivery.notification.NotificationRepository;
import com.bluedelivery.order.application.SearchOrderService;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderedEventHandler {
    
    private final RedisTemplate<String, OrderNotification> redisTemplate;
    private final SearchOrderService searchOrderService;
    private final NotificationRepository notificationRepository;
    
    @Async
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void notifyOrderToShop(OrderedNotificationEvent event) {
        log.info("알림 이벤트 발생 orderId: " + event.getOrder().getOrderId());
        OrderDetails details = searchOrderService.getOrderDetails(event.getOrder());
        OrderNotification notification = OrderNotification.from(details);
        redisTemplate.boundSetOps(OrderNotification.key).add(notification);
        notificationRepository.save(new Notification(notification.serializeJson()));
    }
    
    @Data
    @AllArgsConstructor
    public static class OrderedNotificationEvent {
        private Order order;
    }
}
