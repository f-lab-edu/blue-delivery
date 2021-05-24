package com.delivery.user;

import com.delivery.config.PasswordAuthenticationException;
import com.delivery.utility.EncryptUtils;
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


	public User login(UserLoginDto loginDto) {
		return userRepository.findByEmail(loginDto.getEmail());
	}

	public void updateAccount(UserUpdateAccountDto dto) {
		User user = new User(dto.getEmail(), dto.getNickname(), dto.getPhone(), dto.getPassword(), dto.getDateOfBirth());
		User findUser = userRepository.findByEmail(dto.getEmail());
		if (findUser.checkPasswordEquality(EncryptUtils.sha256(user.getPassword()))) {
			userRepository.update(user);
		} else {
			throw new PasswordAuthenticationException();
		}
	}

	public User getAccount(String email) {
		return userRepository.findByEmail(email);
	}

}
