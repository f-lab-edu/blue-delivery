package com.delivery.user;

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
    public void register(@Valid @RequestBody UserRegisterDto dto) {
        User user = dtoToUser(dto);
        userManagementService.register(user);
    }

    // TODO User에 필요한 데이터가 정해지면 수정
    private User dtoToUser(UserRegisterDto dto) {
        return new User(dto.getEmail());
    }

}
