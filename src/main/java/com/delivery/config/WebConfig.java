package com.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.delivery.shop.businesshour.BusinessHourMapper;
import com.delivery.shop.shop.ShopMapper;
import com.delivery.shop.shop.ShopRepository;
import com.delivery.shop.shop.ShopRepositoryMybatis;
import com.delivery.user.UserRepository;
import com.delivery.user.UserRepositoryHashMap;

@Configuration
public class WebConfig {
    
    @Configuration
    @Profile("dev")
    static class DevConfig {
        @Autowired
        private ShopMapper shopMapper;
        @Autowired
        private BusinessHourMapper businessHourMapper;
        
        @Bean
        public UserRepository userRepository() {
            return new UserRepositoryHashMap();
        }
        
        @Bean
        public ShopRepository shopRepository() {
            return new ShopRepositoryMybatis(shopMapper, businessHourMapper);
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
