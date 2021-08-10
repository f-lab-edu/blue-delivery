package com.bluedelivery.domain.order;

import static com.bluedelivery.OrderData.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderTest {
    
    @Test
    void chicken_15000_X2_plus_sidemenu_1000_is_31000_won() {
        //given
        Order order = order()
                .orderItems(List.of(
                                orderItem().menuName("맛있는 치킨").price(15000).orderItemOptionGroups(List.of(
                                                orderItemOptionGroup().name("사이드메뉴").orderItemOptions(List.of(
                                                                orderItemOption().name("치킨무").price(1000).build()))
                                                        .build()
                                        )).quantity(2)
                                        .build()
                        )
                )
                .build();
        //when
        int amount = order.totalOrderAmount();
        
        //then
        assertThat(amount).isEqualTo(31000);
    }
    
    @Test
    void chicken_15000_X2__plus_sidemenu_1000_plus_pizza_X1_is_41000_won() {
        //given
        Order order = order()
                .orderItems(List.of(
                                orderItem().menuName("맛있는 치킨").price(15000).orderItemOptionGroups(List.of(
                                                orderItemOptionGroup().name("사이드메뉴").orderItemOptions(List.of(
                                                                orderItemOption().name("치킨무").price(1000).build()))
                                                        .build()
                                        )).quantity(2)
                                        .build(),
                                orderItem().menuName("뜨거운 피자").price(10000).orderItemOptionGroups(emptyList())
                                        .build()
                        )
                )
                .build();
        //when
        int amount = order.totalOrderAmount();
        
        //then
        assertThat(amount).isEqualTo(41000);
    }
    
}
