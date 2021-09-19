package com.bluedelivery.order.infra;

import static com.bluedelivery.OrderData.order;
import static com.bluedelivery.OrderData.orderForm;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.bluedelivery.common.event.Outbox;
import com.bluedelivery.common.event.OutboxRepository;
import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.application.impl.OrderHttpService;
import com.bluedelivery.order.application.impl.OrderMapper;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderRepository;
import com.bluedelivery.payment.Payment;
import com.bluedelivery.payment.PaymentService;

@SpringBootTest
@ActiveProfiles("test")
class EventHandlerTest {
    
    private OrderService orderService;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @Autowired
    private OutboxRepository outboxRepository;
    
    @Mock
    private PaymentService paymentService;
    
    @Mock
    private OrderMapper orderMapper;
    
    @BeforeEach
    void setup() {
        orderService = new OrderHttpService(orderRepository, orderMapper, paymentService, publisher);
    }
    
    @Test
    @DirtiesContext
    void if_order_succeed_then_order_and_outbox_saved_into_db() {
        //given
        Order.OrderForm form = orderForm().build();
        Order order = order().build();
        Payment.PaymentForm paymentForm = new Payment.PaymentForm(order);
        given(orderMapper.map(form)).willReturn(order);
        given(paymentService.process(paymentForm)).willReturn(paymentForm.createPayment());
        
        int originalOrderSize = orderRepository.findAll().size();
        int originalOutboxSize = outboxRepository.findAll().size();
        
        //when
        orderService.takeOrder(form);
        List<Order> ordered = orderRepository.findAll();
        List<Outbox> outboxes = outboxRepository.findAll();
        
        //then
        assertThat(ordered.size()).isEqualTo(originalOrderSize + 1);
        assertThat(outboxes.size()).isEqualTo(originalOutboxSize + 1);
    }
}
