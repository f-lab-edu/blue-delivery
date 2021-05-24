package com.delivery.user;

import com.delivery.utility.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class UserManagementController {

	private UserManagementService userManagementService;

	private UserRegisterPasswordValidator userRegisterPasswordValidator;

	private Logger log = LoggerFactory.getLogger(getClass());

	public UserManagementController(UserManagementService userManagementService, UserRegisterPasswordValidator userRegisterPasswordValidator) {
		this.userManagementService = userManagementService;
		this.userRegisterPasswordValidator = userRegisterPasswordValidator;
	}

	@InitBinder("userRegisterDto")
	void initRegisterPasswordValidator(WebDataBinder binder) {
		binder.addValidators(userRegisterPasswordValidator);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDto dto) {
		User user = dtoToUser(dto);
		userManagementService.register(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// TODO User에 필요한 데이터가 정해지면 수정
	private User dtoToUser(UserRegisterDto dto) {
		return new User(dto.getEmail(), dto.getNickname(), dto.getPhone(), EncryptUtils.sha256(dto.getPassword()), dto.getDateOfBirth());
	}

	//로그인 처리
	@PostMapping("/login")
	public void login(@RequestBody UserLoginDto loginDto, HttpServletRequest request) throws Exception {

		User user = userManagementService.login(loginDto);
		HttpSession httpSession = request.getSession();

		if (user.checkPasswordEquality(loginDto.getUserPassword())) {
			log.info("login success");
			httpSession.setAttribute("login", user);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateAccount(@Valid @RequestBody UserUpdateAccountDto dto) {
		userManagementService.updateAccount(dto);
		return ResponseEntity.status(HttpStatus.OK).body("User account update.");
	}

}
