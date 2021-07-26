package com.delivery.authentication;

import java.util.Optional;

import com.delivery.user.web.dto.UserLoginParam;

public interface AuthenticationService {
    String BEARER_PREFIX = "Bearer ";
    
    Optional<Authentication> getAuthentication(String authenticationHeader);
    
    Authentication authenticate(UserLoginParam loginDto);
    
    void expire(Authentication loggedIn);
}
