package com.delivery.aop;

import static com.delivery.aop.AuthenticationAopTest.*;
import static com.delivery.response.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.Authentication;
import com.delivery.user.AuthenticationHolder;

@SpringBootTest(classes = {TestService.class, AuthenticationAop.class})
@EnableAspectJAutoProxy // aop 프록시 생성 관련 빈을 활성화
@AutoConfigureMockMvc
class AuthenticationAopTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    TestService service;
    
    @Test
    @DisplayName("@AuthenticationRequired 메소드 실행시 인증 정보가 없으면 Exception 발생")
    void throwInvalidAuthenticationExceptionTest() {
        AuthenticationHolder.setAuthentication(null);
        ErrorCode error = assertThrows(ApiException.class, () -> service.orderFood()).getError();
        assertThat(error).isEqualTo(NOT_AUTHORIZED_ACCESS);
    }
    
    @Test
    void authenticateTest() {
        AuthenticationHolder.setAuthentication(new Authentication("a@a", "nick", "01012341234"));
        assertDoesNotThrow(() -> service.orderFood());
    }
    
    @Component
    static class TestService {
        
        @AuthenticationRequired
        public void orderFood() {
            System.out.println("order process");
        }
    }
}
