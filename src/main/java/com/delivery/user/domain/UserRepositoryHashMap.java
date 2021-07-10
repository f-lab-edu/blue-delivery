package com.delivery.user.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UserRepositoryHashMap implements UserMapper {
    
    private final Map<String, User> repository;
    
    public UserRepositoryHashMap() {
        this.repository = new HashMap<>();
    }
    
    @Override
    public User save(User user) {
        if (repository.containsKey(user.getEmail())) {
            throw new DuplicateKeyException("key already exists");
        }
        repository.put(user.getEmail(), user);
        return user;
    }
    
    @Override
    public User findUserByEmail(String email) {
        User user = repository.get(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }
    
    @Override
    public void delete(User user) {
        repository.remove(user.getEmail());
    }
    
    @Override
    public void update(User user) {
        if (repository.containsKey(user.getEmail())) {
            repository.put(user.getEmail(), user);
        }
    }
    
}


