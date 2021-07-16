package com.delivery.user.web;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.response.HttpResponse;
import com.delivery.user.web.dto.UserLoginParam.UserLoginRequest;

@RequestMapping("/")
public interface AuthenticationController {
    
    /**
     * 고객 로그인
     *
     * @param loginRequest 로그인 정보
     * @param session      http session
     */
    @PostMapping("login")
    ResponseEntity<HttpResponse<?>> login(@RequestBody UserLoginRequest loginRequest, HttpSession session);
    
}
