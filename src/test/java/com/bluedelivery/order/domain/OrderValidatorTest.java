package com.bluedelivery.order.domain;

import static com.bluedelivery.OrderData.*;
import static com.bluedelivery.order.domain.ExceptionMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bluedelivery.domain.menu.MenuRepository;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;
import com.bluedelivery.domain.user.User;
import com.bluedelivery.domain.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class OrderValidatorTest {
    
    @Mock(lenient = true)
    private Shop shop;
    
    @Mock(lenient = true)
    private MenuRepository menuRepository;
    
    @Mock(lenient = true)
    private UserRepository userRepository;
    
    @Mock(lenient = true)
    private ShopRepository shopRepository;
    
    private OrderValidator orderValidator;
    
    @BeforeEach
    void setup() {
        orderValidator = new OrderValidator(menuRepository, shopRepository, userRepository);
        given(userRepository.findById(1L)).willReturn(Optional.of(new User()));
        given(shopRepository.findById(1L)).willReturn(Optional.of(shop));
        given(menuRepository.findAllById(List.of(1L))).willReturn(List.of(menu().build()));
        given(shop.isOpen()).willReturn(true);
        given(shop.getMinimumOrderAmount()).willReturn(0);
    }
    
    @Test
    void exception_if_shop_is_not_open() {
        //given
        Order order = order().build();
        given(shop.isOpen()).willReturn(false);
        
        //when
        String msg = assertThrows(IllegalStateException.class, () -> orderValidator.validate(order)).getMessage();
        
        //then
        assertThat(msg).contains(SHOP_IS_NOT_OPEN);
    }
    
    @Test
    void exception_if_ordered_item_list_is_empty() {
        //given
        Order order = order().orderItems(Collections.emptyList()).build();
        
        //when
        String msg = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(order)).getMessage();
        
        //then
        assertThat(msg).contains(ORDER_LIST_IS_EMPTY);
    }
    
    @Test
    void exception_if_ordered_item_and_menu_are_different() {
        //given
        Order order = order().orderItems(List.of(orderItem().menuName("옛날양념통닭").build())).build();
        
        //when
        String msg = assertThrows(IllegalStateException.class, () -> orderValidator.validate(order)).getMessage();
        
        //then
        assertThat(msg).contains(ORDERED_AND_MENU_ARE_DIFFERENT);
    }
    
    @Test
    void exception_if_ordered_amount_is_lower_than_minimum_amount() {
        //given
        Order order = order().build();
        given(shop.getMinimumOrderAmount()).willReturn(30000);
        
        //when
        String msg = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(order)).getMessage();
        
        //then
        assertThat(msg).isEqualTo(ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT);
    }
    
    @Test
    void exception_if_user_does_not_exist() {
        //given
        given(userRepository.findById(1L)).willReturn(Optional.empty());
        Order order = order().userId(null).build();
        
        //when
        String msg = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(order)).getMessage();
        
        //then
        assertThat(msg).isEqualTo(ORDER_USER_DOES_NOT_EXIST);
    }
    
    @Test
    void exception_if_order_option_is_different_with_menu_option() {
        //given
        Order order = order()
                .orderItems(List.of(
                        orderItem().orderItemOptionGroups(List.of(
                                        orderItemOptionGroup().orderItemOptions(List.of(
                                                        orderItemOption().name("마요네즈").build()))
                                                .build()))
                                .build()))
                .build();
        
        //when
        String msg = assertThrows(IllegalStateException.class, () -> orderValidator.validate(order)).getMessage();
        
        //then
        assertThat(msg).isEqualTo(ORDERED_AND_MENU_ARE_DIFFERENT);
    
    }
    
    @Test
    void success_when_order_is_validate() {
        //given
        Order order = order().build();
        
        //when //then
        assertDoesNotThrow(() -> orderValidator.validate(order));
    }

}
