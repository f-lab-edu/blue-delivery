package com.delivery.config.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.Authentication;

public class UserAuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (needToBeAuthenticated((HandlerMethod) handler)) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                throw new ApiException(ErrorCode.NOT_AUTHORIZED_ACCESS);
            }
            
            Authentication auth = (Authentication) session.getAttribute(Authentication.KEY);
            if (auth == null || !isSameUser(request, auth)) {
                throw new ApiException(ErrorCode.NOT_AUTHORIZED_ACCESS);
            }
        }
        return true;
    }
    
    private boolean needToBeAuthenticated(HandlerMethod handler) {
        if (AnnotationUtils.findAnnotation(handler.getMethod(), AuthenticationRequired.class) == null
                && AnnotationUtils.findAnnotation(handler.getBeanType(), AuthenticationRequired.class) == null) {
            return false;
        }
        return true;
    }
    
    private boolean isSameUser(HttpServletRequest request, Authentication auth) {
        Map<String, String> pathVariables = (Map<String, String>)
                request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return pathVariables.get("id").equals(auth.getId().toString());
    }
    
}
