package com.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.delivery.user.UserRepository;
import com.delivery.user.UserRepositoryHashMap;

@Configuration
@Profile("dev")
public class RepositoryConfigDev {
    
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryHashMap();
    }
}
