package com.bluedelivery.application.authentication;

import java.util.Optional;

import com.bluedelivery.domain.authentication.Authentication;

public interface AuthenticationService {
    Optional<Authentication> getAuthentication(String authenticationHeader);
    
    Authentication authenticate(LoginTarget loginDto);
    
    void expire(Authentication loggedIn);
    
}
