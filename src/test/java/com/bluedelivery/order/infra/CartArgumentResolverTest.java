package com.bluedelivery.order.infra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bluedelivery.order.interfaces.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class CartArgumentResolverTest {

    private ObjectMapper objectMapper = new ObjectMapper();
    private CartArgumentResolver resolver = new CartArgumentResolver(objectMapper);

    @Test
    void supportParameterTest() throws NoSuchMethodException {
        //given
        MethodParameter methodParameter = new MethodParameter(
                CartArgumentResolverTest.class
                        .getDeclaredMethod("cart", Cart.class),
                0);
    
        //when
        boolean result = resolver.supportsParameter(methodParameter);
    
        //then
        assertThat(result).isTrue();
    }
    
    @Test
    void resolveArgumentTest(@Mock MethodParameter parameter,
                             @Mock ModelAndViewContainer mavContainer,
                             @Mock NativeWebRequest webRequest,
                             @Mock WebDataBinderFactory binderFactory,
                             @Mock HttpServletRequest req) throws Exception {
        //given
        given(webRequest.getNativeRequest()).willReturn(req);
        given(req.getCookies()).willReturn(new Cookie[]{new Cookie("CART", objectMapper.writeValueAsString(
                new Cart()
        ))});
        resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }
    
    public void cart(Cart cart) {
    
    }
}
