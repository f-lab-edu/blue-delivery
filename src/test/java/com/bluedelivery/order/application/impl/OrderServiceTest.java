package com.bluedelivery.order.application.impl;

import static com.bluedelivery.OrderData.orderForm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.application.OrderValidator;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderRepository;
import com.bluedelivery.payment.Payment;
import com.bluedelivery.payment.PaymentService;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    
    private OrderService orderService;
    
    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private OrderValidator orderValidator;
    
    @Mock
    private PaymentService paymentService;
    
    @BeforeEach
    void setup() {
        orderService = new OrderHttpService(orderRepository, orderValidator, paymentService);
    }
    
    @Test
    void if_payment_denied_order_fail() {
        //given
        Payment payment = Payment.create();
        payment.deny();
        Order.OrderForm form = orderForm().build();
        Order order = form.createOrder(orderValidator);
        given(paymentService.process(order)).willReturn(payment);
    
        //when
        assertThrows(IllegalStateException.class, () -> orderService.takeOrder(form));
    
        //then
        verify(orderRepository, never()).save(order);
    }
    
    @Test
    void takeOrderTest() {
        //given
        Payment payment = Payment.create();
        Order.OrderForm form = orderForm().build();
        Order order = form.createOrder(orderValidator);
        given(paymentService.process(order)).willReturn(payment);
        
        //when
        Order result = orderService.takeOrder(form);
    
        //then
        assertThat(result).isEqualTo(order);
        verify(orderRepository, times(1)).save(order);
    }
    
    
}
