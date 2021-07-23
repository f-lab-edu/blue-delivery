package com.delivery.config.interceptor;

import static com.delivery.config.CustomSession.NAME_SPACE;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.delivery.config.CustomSession;

@Repository
public class SessionRedisRepository implements SessionRepository {
    
    private RedisTemplate<String, Object> redisTemplate;
    
    public SessionRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    @Override
    public CustomSession save(CustomSession customSession) {
        var ops
                = redisTemplate.boundHashOps(NAME_SPACE + customSession.getSessionId());
        ops.put(customSession.getSessionId(), customSession);
        ops.expire(customSession.getMaxInactiveDays());
        return customSession;
    }
    
    @Override
    public Optional<CustomSession> findById(String sessionId) {
        var ops =
                redisTemplate.boundHashOps(NAME_SPACE + sessionId);
        CustomSession customSession = (CustomSession) ops.get(sessionId);
        return Optional.ofNullable(customSession);
    }
}
