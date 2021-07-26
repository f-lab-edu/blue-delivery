package com.bluedelivery.authentication;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
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
    
}
