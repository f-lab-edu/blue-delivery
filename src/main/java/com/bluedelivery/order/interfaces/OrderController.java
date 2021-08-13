package com.bluedelivery.order.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedelivery.api.authentication.AuthenticationRequired;
import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.authentication.Authentication;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "주문")
@RequestMapping("/orders")
public interface OrderController {
    
    @ApiOperation(value = "주문 생성 요청", notes = "인증 정보와 장바구니 정보를 바탕으로 주문을 생성한다.")
    @PostMapping
    @AuthenticationRequired
    ResponseEntity<HttpResponse<?>> createOrderRequest(Authentication authentication, @RequestBody Cart cart);
}



