package com.delivery.user;

import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

	private UserRepository userRepository;

	public UserManagementService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void register(User user) {
		userRepository.save(user);
	}

	public User login(UserLoginDto loginDto) {
		return userRepository.findByEmail(loginDto.getEmail());
	}

	public void deleteAccount(DeleteAccountDto dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		if (user.checkPasswordEquality(dto.getPassword())) {
			userRepository.delete(user);
		}
	}
}
