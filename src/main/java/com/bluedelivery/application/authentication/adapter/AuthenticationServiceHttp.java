package com.bluedelivery.application.authentication.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.application.authentication.LoginTarget;
import com.bluedelivery.domain.authentication.Authentication;
import com.bluedelivery.domain.authentication.AuthenticationRepository;
import com.bluedelivery.domain.authentication.TokenType;
import com.bluedelivery.domain.user.User;
import com.bluedelivery.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthenticationServiceHttp implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    
    @Override
    public Optional<Authentication> getAuthentication(String authorization) {
        String token = TokenType.BEARER.extract(authorization);
        return authenticationRepository.findByToken(token);
    }
    
    @Override
    public Authentication authenticate(LoginTarget target) {
        User user = userRepository.findByEmail(target.getEmail()).orElseThrow();
        user.validate(target.getPassword());
        Authentication auth = new Authentication(UUID.randomUUID().toString(), user.getId());
        authenticationRepository.save(auth);
        return auth;
    }
    
    @Override
    public void expire(Authentication loggedIn) {
        authenticationRepository.expire(loggedIn);
    }
}
