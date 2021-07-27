package com.bluedelivery.common.authentication.infra;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bluedelivery.common.authentication.domain.Authentication;
import com.bluedelivery.common.authentication.domain.AuthenticationRepository;

@Repository
public class AuthenticationRedisRepository implements AuthenticationRepository {
    
    public static String NAME_SPACE = "custom:session:";
    private RedisTemplate<String, Object> redis;
    
    public AuthenticationRedisRepository(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }
    
    @Override
    public Authentication save(Authentication authentication) {
        var ops = redis.boundHashOps(NAME_SPACE + authentication.getToken());
        ops.put(authentication.getToken(), authentication);
        ops.expire(authentication.getMaxInactiveTime());
        return authentication;
    }
    
    @Override
    public Optional<Authentication> findByToken(String token) {
        var ops = redis.boundHashOps(NAME_SPACE + token);
        Authentication authentication = (Authentication) ops.get(token);
        return Optional.ofNullable(authentication);
    }
    
    @Override
    public void expire(Authentication loggedIn) {
        var ops = redis.boundHashOps(NAME_SPACE + loggedIn.getToken());
        ops.delete(loggedIn.getToken());
    }
}
