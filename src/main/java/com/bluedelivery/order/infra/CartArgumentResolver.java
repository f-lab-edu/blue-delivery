package com.bluedelivery.order.infra;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bluedelivery.order.interfaces.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CartArgumentResolver implements HandlerMethodArgumentResolver {
    
    private final ObjectMapper objectMapper;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameter().getType().isAssignableFrom(Cart.class);
    }
    
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie[] cookies = req.getCookies();
        Optional<Cookie> cart = Arrays.stream(cookies)
                .filter(x -> x.getName().equals("CART"))
                .findFirst();
        if (cart.isEmpty()) {
            return new Cart();
        }
        return objectMapper.readValue(cart.orElseThrow().getValue(), Cart.class);
    }
}
