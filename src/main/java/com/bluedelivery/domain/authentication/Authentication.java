package com.bluedelivery.domain.authentication;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import org.springframework.core.annotation.AnnotationUtils;

import com.bluedelivery.api.authentication.AuthenticationRequired;

public class Authentication implements Serializable {
    public static String AUTH_STR = "auth";
    private String token;
    private Long userId;
    private Instant creationTime = Instant.now();
    private Duration maxInactiveTime = Duration.ofDays(5);
    private boolean invalidated = false;
    
    protected Authentication() {
    }
    
    public Authentication(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
    
    public static boolean isAnnotated(Method method) {
        if (AnnotationUtils.findAnnotation(method, AuthenticationRequired.class) == null
                && AnnotationUtils.findAnnotation(method.getDeclaringClass(), AuthenticationRequired.class) == null) {
            return false;
        }
        return true;
    }
    
    public void invalidate() {
        this.invalidated = true;
    }
    
    public boolean isInvalidated() {
        if (maxInactiveTime.isNegative()) {
            return false;
        }
        Instant now = Instant.now();
        return invalidated || now.minus(maxInactiveTime).compareTo(now) >= 0;
    }
    
    public String getToken() {
        return token;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public Instant getCreationTime() {
        return creationTime;
    }
    
    public Duration getMaxInactiveTime() {
        return maxInactiveTime;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Authentication that = (Authentication) obj;
        return invalidated == that.invalidated
                && Objects.equals(token, that.token)
                && Objects.equals(userId, that.userId)
                && Objects.equals(creationTime, that.creationTime)
                && Objects.equals(maxInactiveTime, that.maxInactiveTime);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(token, userId, creationTime, maxInactiveTime, invalidated);
    }
}
