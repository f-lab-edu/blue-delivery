package com.bluedelivery.domain.authentication;

import java.util.Optional;

public interface AuthenticationRepository {
    
    Authentication save(Authentication authentication);
    
    Optional<Authentication> findByToken(String token);
    
    void expire(Authentication loggedIn);
}
