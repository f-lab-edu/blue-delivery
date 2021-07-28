package com.bluedelivery.api.authentication;

import static com.bluedelivery.common.response.ErrorCode.INVALID_AUTHENTICATION;
import static com.bluedelivery.common.response.ErrorCode.NOT_AUTHORIZED_ACCESS;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.authentication.Authentication;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAuthInterceptor implements HandlerInterceptor {
    
    private final AuthenticationService authenticationService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (needToBeAuthenticated((HandlerMethod) handler)) {
            Authentication auth = authenticationService.getAuthentication(request.getHeader(AUTHORIZATION))
                    .orElseThrow(() -> new ApiException(INVALID_AUTHENTICATION));
            if (!isSameUser(request, auth) || auth.isInvalidated()) {
                throw new ApiException(NOT_AUTHORIZED_ACCESS);
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
        String userId = pathVariables.get("id");
        if (userId == null) {
            return true;
        }
        return userId.equals(auth.getUserId().toString());
    }
    
}
