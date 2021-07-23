package com.delivery.config;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import com.delivery.user.Authentication;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CustomSession implements Serializable {
    public static String SESSION_STR = "session";
    public static String ID_HEADER_NAME = "Session-Id";
    public static String NAME_SPACE = "custom:session:";
    private String sessionId;
    private Authentication authentication;
    private Instant creationTime = Instant.now();
    private Instant lastAccessedTime = creationTime;
    private Duration maxInactiveDays = Duration.ofDays(5);
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
    
    private String newId() {
        return UUID.randomUUID().toString();
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
    
    public Duration getMaxInactiveDays() {
        return maxInactiveDays;
    }
    
    private void setLastAccessedTime() {
        this.lastAccessedTime = Instant.now();
    }
    
    public boolean isInvalidated() {
        if (maxInactiveDays.isNegative()) {
            return false;
        }
        return invalidated || Instant.now().minus(maxInactiveDays).compareTo(lastAccessedTime) >= 0;
    }
    
    public void setMaxInactiveDays(Duration maxInactiveDays) {
        this.maxInactiveDays = maxInactiveDays;
    }
    
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
