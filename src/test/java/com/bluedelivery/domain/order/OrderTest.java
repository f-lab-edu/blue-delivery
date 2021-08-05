package com.bluedelivery.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bluedelivery.domain.shop.Shop;

@ExtendWith(MockitoExtension.class)
public class OrderTest {
    
    @Mock
    private Shop shop;
    
    @BeforeEach
    void setup() {
    
    }
    
    @Test
    void if_order_created_it_has_status_Wait() {
        //given
        Order order = new Order();
        
        //when
        Order.OrderStatus orderStatus = order.getStatus();
        
        //then
        assertThat(orderStatus).isEqualTo(Order.OrderStatus.WAIT);
    }
    
    @Test
    void exception_if_shop_is_not_open() {
        //given
        given(shop.isOpeningAt()).willReturn(false);
        Order order = new Order(shop);
        
        //when
        //then
        assertThrows(IllegalStateException.class, order::validate);
    }
    
    @Test
    void exception_if_ordered_item_list_is_empty() {
        //given
        given(shop.isOpeningAt()).willReturn(true);
        Order order = new Order(shop, new OrderItemList());
        
        //when
        //then
        assertThrows(IllegalArgumentException.class, order::validate);
    }
    
}
