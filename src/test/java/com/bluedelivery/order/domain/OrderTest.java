package com.bluedelivery.order.domain;

import static com.bluedelivery.Data.chicken;
import static com.bluedelivery.Data.order;
import static com.bluedelivery.order.domain.ExceptionMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.shop.Shop;

@ExtendWith(MockitoExtension.class)
public class OrderTest {
    
    @Mock(lenient = true)
    private Shop shop;
    
    @BeforeEach
    void setup() {
        given(shop.isOpen()).willReturn(true);
        given(shop.getMinimumOrderAmount()).willReturn(0);
    }
    
    @Test
    void exception_if_shop_is_not_open() {
        //given
        Order order = order().shop(shop).build();
        given(shop.isOpen()).willReturn(false);
        
        //when
        String message = assertThrows(IllegalStateException.class, order::validate).getMessage();
        
        //then
        assertThat(message).contains(SHOP_IS_NOT_OPEN);
    }
    
    @Test
    void exception_if_ordered_item_list_is_empty() {
        //given
        Order order = order().shop(shop).orderItemList(new OrderItemList()).build();
        
        //when
        String message = assertThrows(IllegalArgumentException.class, order::validate).getMessage();
        
        //then
        assertThat(message).contains(ORDER_LIST_IS_EMPTY);
    }
    
    @Test
    void exception_if_ordered_item_and_menu_are_different() {
        //given
        Menu ordered = chicken().name("옛날양념통닭").price(10000).build();
        Order order = order().shop(shop).orderItemList(new OrderItemList(OrderItem.from(ordered, 1))).build();
    
        //when
        String message = assertThrows(IllegalStateException.class, order::validate).getMessage();
        
        //then
        assertThat(message).contains(ORDERED_AND_MENU_ARE_DIFFERENT);
    }
    
    @Test
    void exception_if_ordered_amount_is_lower_than_minimum_amount() {
        //given
        Order order = order().shop(shop).build();
        given(shop.getMinimumOrderAmount()).willReturn(30000);
    
        //when
        String message = assertThrows(IllegalArgumentException.class, order::validate).getMessage();
        
        //then
        assertThat(message).isEqualTo(ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT);
    }
    
    @Test
    void exception_if_user_does_not_exist() {
        //given
        Order order = order().shop(shop).user(null).build();
        
        //when
        String message = assertThrows(IllegalArgumentException.class, order::validate).getMessage();
    
        //then
        assertThat(message).isEqualTo(ORDER_USER_DOES_NOT_EXIST);
    
    }
    
    @Test
    void success_when_order_is_validate() {
        //given
        Order order = order().shop(shop).build();
        //when //then
        assertDoesNotThrow(order::validate);
    }
    
}
