package com.bluedelivery.application.authentication;

import static java.util.Objects.nonNull;

import java.util.Optional;

import com.bluedelivery.domain.authentication.Authentication;

public interface AuthenticationService {
    String BEARER_PREFIX = "Bearer ";
    
    Optional<Authentication> getAuthentication(String authenticationHeader);
    
    Authentication authenticate(LoginTarget loginDto);
    
    void expire(Authentication loggedIn);
    
    default String extractToken(String authorization) {
        if (nonNull(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.substring(BEARER_PREFIX.length());
        }
        return "";
    }
}
