package com.delivery.user;

import org.springframework.stereotype.Service;

import com.delivery.exception.PasswordAuthenticationException;

@Service
public class UserManagementService {
    
    private UserRepository userRepository;
    
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void register(UserRegisterDto dto) {
        userRepository.save(dto.toEntity());
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
}
