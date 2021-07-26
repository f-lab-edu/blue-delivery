package com.bluedelivery.authentication;

import java.util.Optional;

import com.bluedelivery.user.web.dto.UserLoginParam;

public interface AuthenticationService {
    String BEARER_PREFIX = "Bearer ";
    
    Optional<Authentication> getAuthentication(String authenticationHeader);
    
    Authentication authenticate(UserLoginParam loginDto);
    
    void expire(Authentication loggedIn);
}
