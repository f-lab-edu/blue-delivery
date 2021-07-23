package com.delivery.config.interceptor;

import java.util.Optional;

import com.delivery.config.CustomSession;

public interface SessionRepository {
    
    CustomSession save(CustomSession customSession);
    
    Optional<CustomSession> findById(String sessionId);
}
