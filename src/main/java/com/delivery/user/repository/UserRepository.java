package com.delivery.user.repository;

import com.delivery.user.dto.LoginDto;
import com.delivery.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    public User login(LoginDto loginDto) throws Exception;

}
