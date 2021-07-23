package com.delivery.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.config.CustomSession;
import com.delivery.config.interceptor.AuthenticationRequired;
import com.delivery.config.interceptor.NotLoggedIn;
import com.delivery.response.HttpResponse;
import com.delivery.user.web.dto.UserLoginParam.UserLoginRequest;
import com.delivery.user.web.dto.UserRegisterParam;

@RequestMapping("/auth")
public interface AuthenticationController {
    
    /**
     * 고객 로그인
     *
     * @param loginRequest 로그인 정보
     *
     */
    @NotLoggedIn
    @PostMapping("login")
    ResponseEntity<HttpResponse<CustomSession>> login(@RequestBody UserLoginRequest loginRequest);
    
    
    /**
     * 고객 회원가입
     *
     * @param registerRequest 회원가입정보
     * @return 가입 성공시 201 CREATED
     */
    @NotLoggedIn
    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody UserRegisterParam.UserRegisterRequest registerRequest);
    
    @AuthenticationRequired
    @PostMapping("/logout")
    ResponseEntity<?> logout(HttpServletRequest request);
    
}
