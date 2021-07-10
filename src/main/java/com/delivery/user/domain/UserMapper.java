package com.delivery.user.domain;

import org.apache.ibatis.annotations.Mapper;

import com.delivery.user.domain.UserRepository;

@Mapper
public interface UserMapper {
    
    User save(User user);
    
    void update(User user);
    
    void delete(User user);
    
    User findUserById(Long id);
    
    User findUserByEmail(String email);
}
