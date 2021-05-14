package com.delivery.user.service;

import com.delivery.user.dto.LoginDto;
import com.delivery.user.model.User;
import com.delivery.user.repository.UserRepository;
import com.delivery.user.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = new UserRepositoryImpl();
    }

    public User login(LoginDto loginDto) throws Exception {
        return userRepository.login(loginDto);
    }
}
