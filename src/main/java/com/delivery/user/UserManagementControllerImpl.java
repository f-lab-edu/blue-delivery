package com.delivery.user;

import static com.delivery.user.UpdateAccountParam.*;
import static com.delivery.user.UserLoginParam.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.user.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.UserRegisterParam.UserRegisterRequest;


@RestController
public class UserManagementControllerImpl implements UserManagementController {
    
    private UserManagementService userManagementService;
    
    private PasswordValidator passwordValidator;
    
    public UserManagementControllerImpl(UserManagementService userManagementService,
                                        PasswordValidator passwordValidator) {
        this.userManagementService = userManagementService;
        this.passwordValidator = passwordValidator;
    }
    
    @InitBinder("userRegisterRequest")
    void initRegisterPasswordValidator(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }
    
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest dto) {
        userManagementService.register(dto.toParam());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    public void login(@RequestBody UserLoginRequest loginDto, HttpSession session) {
        Authentication auth = userManagementService.login(loginDto.toParam());
        session.setAttribute(Authentication.KEY, auth);
    }
    
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id,
                                           @Valid @RequestBody DeleteAccountRequest dto, HttpSession session) {
        userManagementService.deleteAccount(dto.toParam(id));
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    public void updateAccount(@PathVariable("id") Long id,
                              @Valid @RequestBody UpdateAccountRequest dto) {
        userManagementService.updateAccount(dto.toParam(id));
    }
    
}
