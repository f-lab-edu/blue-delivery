package com.delivery.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void insert(User user);

    User findByEmail(String email);
}
