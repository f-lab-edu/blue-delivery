package com.delivery.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    private UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        try { // db에 접근하는 횟수를 줄이기 위해 바로 insert 후 중복되는 key면 예외처리
            userRepository.save(user);
        } catch (DuplicateKeyException ex) {
            throw ex;
        }
    }

}
