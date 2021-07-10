package com.delivery.user.web;

import static com.delivery.response.HttpResponse.*;
import static com.delivery.user.web.dto.UpdateAccountParam.*;
import static com.delivery.user.web.dto.UserLoginParam.*;
import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.response.HttpResponse;
import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.dto.AddressParam.AddressRequest;
import com.delivery.user.web.dto.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.web.dto.UserRegisterParam.UserRegisterRequest;


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
        return ResponseEntity.status(CREATED).build();
    }
    
    public void login(@RequestBody UserLoginRequest loginDto, HttpSession session) {
        Authentication auth = userManagementService.login(loginDto.toParam());
        session.setAttribute(Authentication.KEY, auth);
    }
    
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id,
                                           @Valid @RequestBody DeleteAccountRequest dto, HttpSession session) {
        userManagementService.deleteAccount(dto.toParam(id));
        session.invalidate();
        return ResponseEntity.status(NO_CONTENT).build();
    }
    
    public void updateAccount(@PathVariable("id") Long id,
                              @Valid @RequestBody UpdateAccountRequest dto) {
        userManagementService.updateAccount(dto.toParam(id));
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> addAddress(Long id, AddressRequest addressRequest) {
        userManagementService.addAddress(addressRequest.toParam(id));
        return ResponseEntity.status(CREATED).body(response("address has successfully created"));
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> setMainAddress(Long id, Long addrId) {
        userManagementService.setMainAddress(id, addrId);
        return ResponseEntity.ok(response("Main address has successfully designated"));
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> removeAddress(Long id, Long addrId) {
        userManagementService.removeAddress(id, addrId);
        return ResponseEntity.status(NO_CONTENT).body(response("Given address has successfully removed"));
    }
    
}
