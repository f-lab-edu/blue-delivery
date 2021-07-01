package com.delivery.user;

import java.util.Locale;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;
import com.delivery.exception.ExceptionEnum;
import com.delivery.exception.PasswordAuthenticationException;

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
    
    public void updateAccount(UserUpdateAccountDto dto) {
        User user = new User(dto.getEmail(),
                dto.getNickname(),
                dto.getPhone(),
                dto.getPassword(),
                dto.getDateOfBirth()
        );
        User findUser = userRepository.findByEmail(dto.getEmail());
        if (findUser.checkPasswordEquality(user.getPassword())) {
            userRepository.update(user);
        } else {
            throw new PasswordAuthenticationException();
        }
    }
    
    public void deleteAccount(DeleteAccountDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user.checkPasswordEquality(dto.getPassword())) {
            userRepository.delete(user);
        }
    }
    
    public User getAccount(String email) {
        return userRepository.findByEmail(email);
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
