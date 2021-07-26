package com.bluedelivery.config.interceptor;

import java.util.Optional;

import com.bluedelivery.authentication.Authentication;

public interface AuthenticationRepository {
    
    Authentication save(Authentication authentication);
    
    Optional<Authentication> findByToken(String token);
    
    void expire(Authentication loggedIn);
}
