package com.bluedelivery.order.application;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItemList;

@Service
public class OrderHttpService implements OrderService {
    
    @Transactional
    public Order takeOrder(Long userId, OrderItemList orderItemList) {
        return new Order();
        // TODO 주문 생성
        // TODO ORDER 유효성 검사 order.validate(menus);
        // TODO 결제 (mock)
        // TODO 결제 완료 상태로 변경
        // TODO 저장
    }
}
