package com.delivery.config.interceptor;

import static com.delivery.config.CustomSession.ID_HEADER_NAME;
import static com.delivery.config.CustomSession.SESSION_STR;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.delivery.config.CustomSession;
import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.Authentication;

public class UserAuthInterceptor implements HandlerInterceptor {
    
    private final SessionRepository sessionRepository;
    
    public UserAuthInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (needToBeAuthenticated((HandlerMethod) handler)) {
            String sessionId = request.getHeader(ID_HEADER_NAME);
            CustomSession session = sessionRepository.findById(sessionId)
                    .orElseThrow(() -> new ApiException(ErrorCode.NOT_AUTHORIZED_ACCESS));
            
            if (session.isInvalidated()) {
                throw new ApiException(ErrorCode.NOT_AUTHORIZED_ACCESS);
            }
            
            Authentication auth = session.getAuthentication();
            request.setAttribute(SESSION_STR, session);
            if (auth == null || !isSameUser(request, auth)) {
                throw new ApiException(ErrorCode.NOT_AUTHORIZED_ACCESS);
            }
        }
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) {
        if (needToBeAuthenticated((HandlerMethod) handler)) {
            CustomSession session = (CustomSession) request.getAttribute(SESSION_STR);
            sessionRepository.save(session);
        }
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
        return userId.equals(auth.getId().toString());
    }
    
}
