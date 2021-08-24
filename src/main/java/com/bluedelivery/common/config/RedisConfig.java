package com.bluedelivery.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.bluedelivery.domain.authentication.Authentication;
import com.bluedelivery.domain.authentication.AuthenticationRepository;
import com.bluedelivery.infra.authentication.AuthenticationRedisRepository;
import com.bluedelivery.order.infra.OrderNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {
    
    @Value("${spring.redis.host}")
    public String host;
    
    @Value("${spring.redis.port}")
    public String port;
    
    @Bean
    public AuthenticationRepository authenticationRepository(RedisTemplate<String, Object> redisTemplate) {
        return new AuthenticationRedisRepository(redisTemplate);
    }
    
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(Integer.parseInt(port));
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        var serializer = new Jackson2JsonRedisSerializer(Authentication.class);
        serializer.setObjectMapper(objectMapper());
        
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }
    
    @Bean
    public RedisTemplate<String, OrderNotification> orderNotificationRedisTemplate(
            ObjectMapper om, RedisConnectionFactory rcf) {
        var serializer = new Jackson2JsonRedisSerializer<>(OrderNotification.class);
        var redisTemplate = new RedisTemplate<String, OrderNotification>();
        serializer.setObjectMapper(om);
        redisTemplate.setConnectionFactory(rcf);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModules(new JavaTimeModule());
        return mapper;
    }
}
