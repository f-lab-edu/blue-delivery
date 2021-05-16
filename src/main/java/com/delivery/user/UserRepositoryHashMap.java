package com.delivery.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryHashMap implements UserRepository {

    Map<String, User> repository;

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
        return repository.get(email);
    }

}


