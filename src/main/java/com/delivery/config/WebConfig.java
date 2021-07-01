package com.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.delivery.shop.businesshour.BusinessHourMapper;
import com.delivery.shop.shop.ShopMapper;
import com.delivery.shop.shop.ShopRepository;
import com.delivery.user.UserMapper;
import com.delivery.user.UserRepository;
import com.delivery.user.UserRepositoryHashMap;

@Configuration
public class WebConfig {
    
    @Configuration
    @Profile("dev")
    static class DevConfig {
        
        @Autowired
        UserMapper userMapper;
        
        @Bean
        public UserRepository userRepository() {
            return userMapper;
        }
    }
    
    @Configuration
    @Profile("test")
    static class TestConfig {
        @Bean
        public UserRepository userRepository() {
            return new UserRepositoryHashMap();
        }
        
    }
}
