package com.bluedelivery.authentication;

import static java.util.Objects.nonNull;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.config.interceptor.AuthenticationRepository;
import com.bluedelivery.user.domain.User;
import com.bluedelivery.user.domain.UserRepository;
import com.bluedelivery.user.web.dto.UserLoginParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    
    @Override
    public Optional<Authentication> getAuthentication(String authorization) {
        String token = extractToken(authorization);
        return authenticationRepository.findByToken(token);
    }
    
    @Override
    public Authentication authenticate(UserLoginParam target) {
        User user = userRepository.findByEmail(target.getEmail()).orElseThrow();
        boolean validate = user.validate(target.getPassword());
        if (!validate) {
            throw new InvalidLoginException();
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
