package com.bluedelivery.common.authentication.application;

import java.util.Optional;

import com.bluedelivery.common.authentication.domain.Authentication;

public interface AuthenticationService {
    String BEARER_PREFIX = "Bearer ";
    
    Optional<Authentication> getAuthentication(String authenticationHeader);
    
    Authentication authenticate(LoginTarget loginDto);
    
    void expire(Authentication loggedIn);
}
