package com.delivery.user;

import java.util.Locale;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;
import com.delivery.exception.ExceptionEnum;

@Service
public class UserManagementService {
    
    private UserRepository userRepository;
    
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void register(UserRegisterDto dto) {
        try {
            userRepository.save(dto.toEntity());
        } catch (DuplicateKeyException ex) {
            throwDuplicateInfoException(ex);
        }
    }
    
    public User login(UserLoginDto loginDto) {
        return userRepository.findByEmail(loginDto.getEmail());
    }
    
    public void updateAccount(UpdateAccountParam param) {
        User findUser = findUserByIdAndCheckNotNull(param.getId());
        findUser.validate(param.getEmail(), param.getPassword());
        findUser.changePhone(param.getPhone());
        findUser.changeAddress(param.getAddress());
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
            throw new ApiException(ExceptionEnum.USER_NOT_FOUND);
        }
        return user;
    }
    
    private void throwDuplicateInfoException(DuplicateKeyException ex) {
        String message = ex.getMessage();
        String msg = message.substring(message.lastIndexOf("for key")).toLowerCase(Locale.ROOT);
        if (msg.contains("email")) {
            throw new ApiException(ExceptionEnum.DUPLICATE_EMAIL);
        }
        if (msg.contains("nickname")) {
            throw new ApiException(ExceptionEnum.DUPLICATE_NICKNAME);
        }
        if (msg.contains("phone")) {
            throw new ApiException(ExceptionEnum.DUPLICATE_PHONE);
        }
    }
}
