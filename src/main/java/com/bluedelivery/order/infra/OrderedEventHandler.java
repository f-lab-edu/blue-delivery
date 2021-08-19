package com.bluedelivery.order.infra;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.bluedelivery.order.application.SearchOrderService;
import com.bluedelivery.order.domain.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderedEventHandler {
    
    private final OrderNotificationRepository orderNotificationRepository;
    private final SearchOrderService searchOrderService;
    
    @Async
    @TransactionalEventListener
    public void notifyOrderToShop(OrderedNotificationEvent event) {
        log.info("알림 이벤트 발생 orderId: " + event.getOrderId());
        OrderDetails details = searchOrderService.getOrderDetails(event.getOrderId());
        orderNotificationRepository.save(OrderNotification.from(details));
    }
    
    @Data
    @AllArgsConstructor
    public static class OrderedNotificationEvent {
        private Long orderId;
        private Long shopId;
        private Long userId;
        private Long paymentId;
    }
}
