package com.delivery.config;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

import com.delivery.user.Authentication;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CustomSession implements Serializable {
    public static String SESSION_STR = "session";
    public static String NAME_SPACE = "custom:session:";
    private String sessionId;
    private Authentication authentication;
    private Instant creationTime = Instant.now();
    private Instant lastAccessedTime = creationTime;
    private Duration maxInactiveTime = Duration.ofDays(5);
    private boolean invalidated = false;
    
    public CustomSession() {
    }
    
    public CustomSession(String sessionId, Authentication auth) {
        this.sessionId = sessionId;
        this.authentication = auth;
    }
    
    public void invalidate() {
        this.invalidated = true;
    }
    
    public Authentication getAuthentication() {
        setLastAccessedTime();
        return authentication;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public Instant getCreationTime() {
        return creationTime;
    }
    
    public Instant getLastAccessedTime() {
        return lastAccessedTime;
    }
    
    public Duration getMaxInactiveTime() {
        return maxInactiveTime;
    }
    
    private void setLastAccessedTime() {
        this.lastAccessedTime = Instant.now();
    }
    
    public boolean isInvalidated() {
        if (maxInactiveTime.isNegative()) {
            return false;
        }
        return invalidated || Instant.now().minus(maxInactiveTime).compareTo(lastAccessedTime) >= 0;
    }
    
    public void setMaxInactiveTime(Duration maxInactiveTime) {
        this.maxInactiveTime = maxInactiveTime;
    }
    
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
