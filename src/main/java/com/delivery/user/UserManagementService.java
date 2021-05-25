package com.delivery.user;

import org.springframework.stereotype.Service;

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
		User user = userRepository.findByEmail(dto.getEmail());
		if (user.checkPasswordEquality(dto.getPassword())) {
			userRepository.update(dto);
		}
	}

	public void deleteAccount(DeleteAccountDto dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		if (user.checkPasswordEquality(dto.getPassword())) {
			userRepository.delete(user);
		}
	}
}
