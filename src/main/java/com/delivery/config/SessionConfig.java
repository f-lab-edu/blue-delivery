package com.delivery.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.delivery.user.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * @EnableRedisHttpSession 을 사용해야하는데, 설정파일에서 `spring:session:store-type: redis` 로 대신함.
 * @EnableRedisHttpSession: Spring session이 지원하는 filter 구현체를 사용하게 함. (springSessionRepositoryFilter 빈)
 * https://docs.spring.io/spring-session/docs/2.5.1/reference/html5/#httpsession
 */
@RequiredArgsConstructor
@EnableRedisHttpSession(redisNamespace = "spring:session")
@Configuration
public class SessionConfig {

    private final RedisProperties redisProperties;
    
    /**
     * RedisConnectionFactory (Lettuce or Jedis) : Spring Session을 Redis에 연결시켜주는 역할
     * Spring & Redis를 사용하기 위해선 IoC Container를 통해 Redis 저장소와 연결해야 하는데
     * 연결을 위해서 Connector가 필요하고, Connector를 생성하기 위해 ConnectionFactory가 사용된다.
     *
     * - 사용자 요청이 들어오면 Spring Session 구현체인 SessionRepositoryFilter가 session을 storage에 저장할텐데
     * - 저장하기 위해선 저장소인 Redis와 연결되어 있어야 하고, 연결을 위해 RedisConnectionFactory가 필요한 것
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }
    
    @Bean
    public RedisTemplate<String, Authentication> redisTemplate() {
        RedisTemplate<String, Authentication> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Authentication.class));
        return redisTemplate;
    }
}
