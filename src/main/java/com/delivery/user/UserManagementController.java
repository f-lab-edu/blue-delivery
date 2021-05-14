package com.delivery.user;

import com.delivery.utility.EncryptUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class UserManagementController {

    private UserManagementService userManagementService;

    private UserRegisterPasswordValidator validator;

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

}
