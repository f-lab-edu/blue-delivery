package com.bluedelivery.order.interfaces;

import static com.bluedelivery.common.response.HttpResponse.response;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.authentication.Authentication;
import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.domain.Order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderControllerImpl implements OrderController {
    
    private final OrderService orderService;
    
    public ResponseEntity<HttpResponse<?>> createOrderRequest(Authentication authentication, Cart cart) {
        Order order = orderService.takeOrder(authentication.getUserId(), cart.toOrderItemList());
        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).body(response(order));
    }
}
