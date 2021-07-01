package com.delivery.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryHashMap implements UserRepository {
    
    private final Map<String, User> repository;
    
    public UserRepositoryHashMap() {
        this.repository = new HashMap<>();
    }
    
    @Override
    public void save(User user) {
        if (repository.containsKey(user.getEmail())) {
            throw new DuplicateKeyException("key already exists");
        }
        repository.put(user.getEmail(), user);
    }
    
    @Override
    public User findByEmail(String email) {
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
    
    @Override
    public User findUserById(Long id) {
        throw new UnsupportedOperationException();
    }
    
}


