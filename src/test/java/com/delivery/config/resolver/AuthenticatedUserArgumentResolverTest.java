package com.delivery.config.resolver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;

import com.delivery.user.Authentication;

class AuthenticatedUserArgumentResolverTest {
    
    @Test
    @DisplayName("리졸버가 파라미터에 @Authenticated가 있고 Authentcation 타입이면 true를 리턴한다.")
    void argument() throws Exception {
        //given
        AuthenticatedUserArgumentResolver resolver = new AuthenticatedUserArgumentResolver();
        MethodParameter methodParameter = new MethodParameter(
                AuthenticatedUserArgumentResolverTest.class
                        .getDeclaredMethod("authenticate", Authentication.class, String.class),
                0);
        
        //when
        boolean result = resolver.supportsParameter(methodParameter);
        
        //then
        assertThat(result).isTrue();
    }
    
    
    @Test
    @DisplayName("리졸버가 파라미터에 @Authenticated가 있고 Authentcation 타입인 경우 외에는 false를 리턴한다.")
    void argumentFail() throws Exception {
        //given
        AuthenticatedUserArgumentResolver resolver = new AuthenticatedUserArgumentResolver();
        MethodParameter methodParameter = new MethodParameter(
                AuthenticatedUserArgumentResolverTest.class
                        .getDeclaredMethod("authenticate", Authentication.class, String.class),
                1);
        
        //when
        boolean result = resolver.supportsParameter(methodParameter);
        
        //then
        assertThat(result).isFalse();
    }
    
    void authenticate(@Authenticated Authentication auth, String nothing) {
    }
}
