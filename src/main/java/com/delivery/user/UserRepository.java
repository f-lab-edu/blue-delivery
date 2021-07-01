package com.delivery.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    
    void save(User user);
    
    void update(User user);
    
    void delete(User user);
    
    User findUserById(Long id);
    
    User findUserByEmail(String email);
}
