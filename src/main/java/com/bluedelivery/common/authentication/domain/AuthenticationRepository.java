package com.bluedelivery.common.authentication.domain;

import java.util.Optional;

public interface AuthenticationRepository {
    
    Authentication save(Authentication authentication);
    
    Optional<Authentication> findByToken(String token);
    
    void expire(Authentication loggedIn);
}
