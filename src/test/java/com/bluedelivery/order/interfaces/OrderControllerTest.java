package com.bluedelivery.order.interfaces;

import static com.bluedelivery.OrderData.*;
import static com.bluedelivery.common.response.HttpResponse.SUCCESS;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.bluedelivery.api.authentication.AuthenticatedUserArgumentResolver;
import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.domain.authentication.Authentication;
import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;
import com.bluedelivery.order.interfaces.impl.OrderControllerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    
    private static String VALID_TOKEN = "valid token";
    private static Long VALID_USER_ID = 1L;
    private static Long SHOP_ID = 1L;
    private static Long ORDER_ID = 1L;
    
    private MockMvc mockMvc;
    private OrderService orderService = Mockito.mock(OrderService.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private OrderController orderController = new OrderControllerImpl(orderService);
    private Authentication authentication = new Authentication(VALID_TOKEN, VALID_USER_ID);
    
    @BeforeEach
    void setup(@Mock(lenient = true) AuthenticationService authenticationService) {
        given(authenticationService.getAuthentication(VALID_TOKEN)).willReturn(Optional.of(authentication));
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setCustomArgumentResolvers(
                        new AuthenticatedUserArgumentResolver(authenticationService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }
    
    @Test
    void orderTest() throws Exception {
        //given
        Cart.CartItem cartItem = cartItem().build();
        Cart cart = new Cart(SHOP_ID, List.of(cartItem));
        Order order = order().orderId(ORDER_ID).orderItems(List.of(OrderItem.from(cartItem))).build();
        given(orderService.takeOrder(orderForm().build())).willReturn(order);
        
        //when
        ResultActions perform = mockMvc.perform(post("/orders")
                .content(objectMapper.writeValueAsString(cart))
                .header(AUTHORIZATION, VALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON));
        
        //then
        perform
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("/orders/" + ORDER_ID))
                .andExpect(jsonPath("$.result").value(SUCCESS))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
    
}
