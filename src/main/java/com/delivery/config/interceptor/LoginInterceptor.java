package com.delivery.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.Authentication;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (mustCheckIfLoggedIn((HandlerMethod) handler)) {
            HttpSession session = request.getSession(false);
            if (isLoggedIn(session)) {
                throw new ApiException(ErrorCode.ALREADY_LOGGED_IN);
            }
        }
        return true;
    }
    
    private boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute(Authentication.KEY) != null;
    }
    
    private boolean mustCheckIfLoggedIn(HandlerMethod handler) {
        if (AnnotationUtils.findAnnotation(handler.getMethod(), NotLoggedIn.class) == null) {
            return false;
        }
        return true;
    }
}
