package com.bluedelivery.order.interfaces;

import static com.bluedelivery.Data.chicken;
import static com.bluedelivery.Data.order;
import static com.bluedelivery.common.response.HttpResponse.SUCCESS;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;

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
import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.order.application.OrderService;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.infra.CartArgumentResolver;
import com.bluedelivery.order.interfaces.Cart.CartItem;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    
    private static String VALID_TOKEN = "valid token";
    private static  Long VALID_USER_ID = 1L;
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
                        new AuthenticatedUserArgumentResolver(authenticationService),
                        new CartArgumentResolver(objectMapper))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }
    
    @Test
    void orderTest() throws Exception {
        //given
        Menu chicken = chicken().build();
        Cart cart = new Cart(SHOP_ID, List.of(CartItem.from(chicken, 1)));
        Order order = order().orderId(ORDER_ID).orderItemList(cart.toOrderItemList()).build();
        given(orderService.takeOrder(VALID_USER_ID, cart.toOrderItemList())).willReturn(order);
        
        //when
        ResultActions perform = mockMvc.perform(post("/orders")
                .cookie(new Cookie("CART", objectMapper.writeValueAsString(cart)))
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
