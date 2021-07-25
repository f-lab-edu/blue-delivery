package com.delivery.config.interceptor;

import java.util.Optional;

import com.delivery.authentication.Authentication;

public interface AuthenticationRepository {
    
    Authentication save(Authentication authentication);
    
    Optional<Authentication> findByToken(String token);
    
    void expire(Authentication loggedIn);
}
