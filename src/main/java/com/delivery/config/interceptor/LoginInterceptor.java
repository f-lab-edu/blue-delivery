package com.delivery.config.interceptor;

import static com.delivery.config.CustomSession.ID_HEADER_NAME;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.delivery.config.CustomSession;
import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;

public class LoginInterceptor implements HandlerInterceptor {
    
    private SessionRepository sessionRepository;
    
    public LoginInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (mustCheckIfLoggedIn((HandlerMethod) handler)) {
            String sessionId = request.getHeader(ID_HEADER_NAME);
            if (isLoggedIn(sessionId)) {
                throw new ApiException(ErrorCode.ALREADY_LOGGED_IN);
            }
        }
        return true;
    }
    
    private boolean isLoggedIn(String sessionId) {
        Optional<CustomSession> sessionBox = sessionRepository.findById(sessionId);
        if (sessionBox.isPresent() && isValid(sessionBox)) {
            return true;
        }
        return false;
    }
    
    private boolean isValid(Optional<CustomSession> sessionBox) {
        return !sessionBox.get().isInvalidated();
    }
    
    private boolean mustCheckIfLoggedIn(HandlerMethod handler) {
        if (AnnotationUtils.findAnnotation(handler.getMethod(), NotLoggedIn.class) == null) {
            return false;
        }
        return true;
    }
}
