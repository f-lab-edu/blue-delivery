package com.delivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.delivery.restaurant.RestaurantMapper;
import com.delivery.restaurant.RestaurantRepository;
import com.delivery.restaurant.RestaurantRepositoryMybatis;
import com.delivery.restaurant.businesshour.BusinessHourMapper;
import com.delivery.user.UserRepository;
import com.delivery.user.UserRepositoryHashMap;

@Configuration
public class WebConfig {
    
    @Configuration
    @Profile("dev")
    static class DevConfig {
        @Autowired
        private RestaurantMapper restaurantMapper;
        @Autowired
        private BusinessHourMapper businessHourMapper;
        
        @Bean
        public UserRepository userRepository() {
            return new UserRepositoryHashMap();
        }
        
        @Bean
        public RestaurantRepository restaurantRepository() {
            return new RestaurantRepositoryMybatis(restaurantMapper, businessHourMapper);
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
