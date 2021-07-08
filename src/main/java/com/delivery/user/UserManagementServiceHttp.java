package com.delivery.user;

import java.util.Locale;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;

@Service
public class UserManagementServiceHttp implements UserManagementService {
    
    private UserRepository userRepository;
    
    public UserManagementServiceHttp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void register(UserRegisterParam dto) {
        try {
            userRepository.save(dto.toEntity());
        } catch (DuplicateKeyException ex) {
            throwDuplicateInfoException(ex);
        }
    }
    
    public Authentication login(UserLoginParam param) {
        User found = findUserByEmailAndCheckNotNull(param.getEmail());
        found.validate(param.getEmail(), param.getPassword());
        return new Authentication(found.getEmail(), found.getNickname(), found.getPhone());
    }
    
    public void updateAccount(UpdateAccountParam param) {
        User findUser = findUserByIdAndCheckNotNull(param.getId());
        findUser.validate(param.getEmail(), param.getPassword());
        findUser.changePhone(param.getPhone());
        findUser.changeNickname(param.getNickname());
        userRepository.update(findUser);
    }
    
    public void deleteAccount(DeleteAccountParam param) {
        User user = findUserByIdAndCheckNotNull(param.getId());
        user.validate(param.getEmail(), param.getPassword());
        userRepository.delete(user);
    }
    
    private User findUserByIdAndCheckNotNull(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }
    
    private User findUserByEmailAndCheckNotNull(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }
    
    private void throwDuplicateInfoException(DuplicateKeyException ex) {
        String message = ex.getMessage();
        String msg = message.substring(message.lastIndexOf("for key")).toLowerCase(Locale.ROOT);
        if (msg.contains("email")) {
            throw new ApiException(ErrorCode.DUPLICATE_EMAIL);
        }
        if (msg.contains("nickname")) {
            throw new ApiException(ErrorCode.DUPLICATE_NICKNAME);
        }
        if (msg.contains("phone")) {
            throw new ApiException(ErrorCode.DUPLICATE_PHONE);
        }
    }
}
