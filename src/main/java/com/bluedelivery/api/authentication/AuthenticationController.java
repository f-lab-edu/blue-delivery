package com.bluedelivery.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.authentication.Authentication;

@RequestMapping("/auth")
public interface AuthenticationController {
    
    /**
     * 고객 로그인
     *
     * @param loggedIn     이미 인증된 객체
     * @param loginRequest 로그인 정보
     */
    @PostMapping("/login")
    ResponseEntity<HttpResponse<Authentication>> login(Authentication loggedIn,
                                                       @RequestBody LoginRequest loginRequest);
    
    @PostMapping("/logout")
    ResponseEntity<?> logout(Authentication loggedIn);
    
}
