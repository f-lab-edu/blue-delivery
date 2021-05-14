package com.delivery.user.repository;

import com.delivery.user.dto.LoginDto;
import com.delivery.user.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private static Map<Long, User> repository = new HashMap();

    @Override
    public User login(LoginDto loginDto) throws Exception {
        return repository.get(loginDto);
    }

}
