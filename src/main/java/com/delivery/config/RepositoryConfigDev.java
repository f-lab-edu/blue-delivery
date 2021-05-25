package com.delivery.config;

import com.delivery.user.UserRepository;
import com.delivery.user.UserRepositoryHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class RepositoryConfigDev {

	@Bean
	public UserRepository userRepository() {
		return new UserRepositoryHashMap();
	}
}