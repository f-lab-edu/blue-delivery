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

    private UserRegisterPasswordValidator validator;

    private Logger log = LoggerFactory.getLogger(getClass());

    public UserManagementController(UserManagementService userManagementService, UserRegisterPasswordValidator validator) {
        this.userManagementService = userManagementService;
        this.validator = validator;
    }

    @InitBinder
    void initRegisterPasswordValidator(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDto dto) {
        User user = dtoToUser(dto);
        userManagementService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO User에 필요한 데이터가 정해지면 수정
    private User dtoToUser(UserRegisterDto dto) {
        return new User(dto.getEmail(), EncryptUtils.sha256(dto.getPassword()));
    }

    //로그인 처리
    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto, HttpServletRequest request) throws Exception {

        User user = userManagementService.login(loginDto);
        HttpSession httpSession = request.getSession();

        if (!isPasswordEquals(loginDto, user)) {
            return;
        } else {
            log.info("login success");
            httpSession.setAttribute("login", user);
        }
    }

    private boolean isPasswordEquals(LoginDto loginDto, User user) {
        return user == null || loginDto.getUserPassword().equals(user.getPassword());
    }

}
