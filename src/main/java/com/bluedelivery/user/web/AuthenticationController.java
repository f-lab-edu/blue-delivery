package com.bluedelivery.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedelivery.authentication.Authentication;
import com.bluedelivery.response.HttpResponse;
import com.bluedelivery.user.web.dto.UserLoginParam.UserLoginRequest;

@RequestMapping("/auth")
public interface AuthenticationController {
    
    /**
     * 고객 로그인
     *
     * @param loggedIn     이미 인증된 객체
     * @param loginRequest 로그인 정보
     */
    @PostMapping("login")
    ResponseEntity<HttpResponse<Authentication>> login(Authentication loggedIn,
                                                       @RequestBody UserLoginRequest loginRequest);
    
    @PostMapping("/logout")
    ResponseEntity<?> logout(Authentication loggedIn);
    
}
