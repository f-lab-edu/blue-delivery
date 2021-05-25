package com.delivery.user;

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

	@PostMapping("/update")
	public ResponseEntity<String> updateAccount(@Valid @RequestBody UserUpdateAccountDto dto) {
		userManagementService.updateAccount(dto);
		return ResponseEntity.status(HttpStatus.OK).body("User account update.");
	}

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
        userManagementService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginDto loginDto, HttpServletRequest request) {

        User user = userManagementService.login(loginDto);
        HttpSession httpSession = request.getSession();

        if (user.checkPasswordEquality(loginDto.getUserPassword())) {
            log.info("login success");
            Authentication auth = new Authentication(
                    user.getEmail(),
                    user.getNickname(),
                    user.getPhone()
            );
            httpSession.setAttribute("auth", auth);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAccount(@Valid @RequestBody DeleteAccountDto dto, HttpSession session) {
        userManagementService.deleteAccount(dto);
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User account deleted.");
    }

}
