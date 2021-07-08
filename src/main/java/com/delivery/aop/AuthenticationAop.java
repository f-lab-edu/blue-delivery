package com.delivery.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.AuthenticationHolder;


@Aspect
@Component
public class AuthenticationAop {
    
    @Pointcut("@annotation(com.delivery.aop.AuthenticationRequired)")
    public void pointcut() {
    }
    
    @Before("pointcut()")
    public void authenticate() {
        if (!AuthenticationHolder.hasAuthentication()) {
            throw new ApiException(ErrorCode.NOT_AUTHORIZED_ACCESS);
        }
    }
    
}
