package com.bluedelivery.common.authentication.application.adapter;

import static java.util.Objects.nonNull;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.common.authentication.application.AuthenticationFailedException;
import com.bluedelivery.common.authentication.application.AuthenticationService;
import com.bluedelivery.common.authentication.application.LoginTarget;
import com.bluedelivery.common.authentication.domain.Authentication;
import com.bluedelivery.common.authentication.domain.AuthenticationRepository;
import com.bluedelivery.user.domain.User;
import com.bluedelivery.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthenticationServiceHttp implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    
    @Override
    public Optional<Authentication> getAuthentication(String authorization) {
        String token = extractToken(authorization);
        return authenticationRepository.findByToken(token);
    }
    
    @Override
    public Authentication authenticate(LoginTarget target) {
        User user = userRepository.findByEmail(target.getEmail()).orElseThrow();
        boolean validate = user.validate(target.getPassword());
        if (!validate) {
            throw new AuthenticationFailedException();
        }
        Authentication auth = new Authentication(UUID.randomUUID().toString(), user.getId());
        authenticationRepository.save(auth);
        return auth;
    }
    
    @Override
    public void expire(Authentication loggedIn) {
        authenticationRepository.expire(loggedIn);
    }
    
    private String extractToken(String authorization) {
        if (nonNull(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.substring(BEARER_PREFIX.length());
        }
        return "";
    }
}
