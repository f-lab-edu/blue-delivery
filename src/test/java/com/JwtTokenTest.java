package com;

import static com.bluedelivery.application.authentication.AuthenticationService.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.application.authentication.LoginTarget;
import com.bluedelivery.application.authentication.adapter.JwtAuthenticationService;
import com.bluedelivery.domain.authentication.Authentication;
import com.bluedelivery.domain.user.User;
import com.bluedelivery.domain.user.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenTest {
    
    private AuthenticationService authenticationService;
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private User user = user();
    private String token;
    
    @BeforeEach
    void create_token() {
        given(userRepository.findByEmail("email")).willReturn(Optional.of(user));
    }
    
    @Test
    void when_authenticated_then_return_Authentication() {
        Date until = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        authenticationService = new JwtAuthenticationService(key, until, userRepository);
        
        Authentication authentication = authenticationService.authenticate(new LoginTarget("email", ""));
        assertThat(authentication).isNotNull();
    }
    
    @Test
    void when_token_valid_then_return_Authentication() {
        Date until = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        authenticationService = new JwtAuthenticationService(key, until, userRepository);
        getToken(until, user);
        
        Optional<Authentication> authentication = authenticationService.getAuthentication(BEARER_PREFIX + token);
        assertThat(authentication.isPresent()).isTrue();
    }
    
    @Test
    void when_token_expired_then_return_null() {
        Date until = Date.from(Instant.now().plus(1, ChronoUnit.MILLIS));
        authenticationService = new JwtAuthenticationService(key, until, userRepository);
        getToken(until, user);
        
        Optional<Authentication> authentication = authenticationService.getAuthentication(BEARER_PREFIX + token);
        assertThat(authentication.isEmpty()).isTrue();
    }
    
    private void getToken(Date until, User user) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        Map<String, String> payloads = new HashMap<>();
        payloads.put("userId", user.getId().toString());
        
        token = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(until)
                .signWith(key)
                .compact();
    }
    
    private User user() {
        return new User(1L, "email", "");
    }
}
