package com.bluedelivery.order.domain;

import static com.bluedelivery.OrderData.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderTest {
    
    @Test
    void chicken_15000_with_option_1000_X2_is_32000_won() {
        //given
        Order order = order()
                .orderItems(List.of(
                        orderItem().menuName("맛있는 치킨").price(15000).orderItemOptionGroups(List.of(
                                        orderItemOptionGroup().name("치킨옵션").orderItemOptions(List.of(
                                                        orderItemOption().name("치즈추가").price(1000).build()))
                                                .build()))
                                .quantity(2).build()
                )).build();
        //when
        int amount = order.totalOrderAmount();
        
        //then
        assertThat(amount).isEqualTo(32000);
    }
    
    @Test
    void chicken_15000_with_option_1000_plus_same_chicken_without_option_is_31000_won() {
        Order order = order()
                .orderItems(List.of(
                        orderItem().menuName("맛있는 치킨").price(15000).orderItemOptionGroups(List.of(
                                orderItemOptionGroup().name("치킨옵션").orderItemOptions(List.of(
                                                orderItemOption().name("치즈추가").price(1000).build()))
                                        .build()
                        )).quantity(1).build(),
                        orderItem().menuName("맛있는 치킨").price(15000).orderItemOptionGroups(Collections.emptyList())
                                .quantity(1).build()
                )).build();
        //when
        int amount = order.totalOrderAmount();
        
        //then
        assertThat(amount).isEqualTo(31000);
    }
    
    @Test
    void chicken_15000_X2_with_option_1000_plus_pizza_X1_is_42000_won() {
        //given
        Order order = order()
                .orderItems(List.of(
                        orderItem().menuName("맛있는 치킨").price(15000).orderItemOptionGroups(List.of(
                                orderItemOptionGroup().name("사이드메뉴").orderItemOptions(List.of(
                                                orderItemOption().name("치킨무").price(1000).build()))
                                        .build()
                        )).quantity(2).build(),
                        orderItem().menuName("뜨거운 피자").price(10000).orderItemOptionGroups(emptyList())
                                .build()))
                .build();
        //when
        int amount = order.totalOrderAmount();
        
        //then
        assertThat(amount).isEqualTo(42000);
    }
    
}
