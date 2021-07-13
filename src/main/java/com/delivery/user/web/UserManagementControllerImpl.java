package com.delivery.user.web;

import static com.delivery.response.HttpResponse.*;
import static com.delivery.user.web.dto.UpdateAccountParam.*;
import static com.delivery.user.web.dto.UserLoginParam.*;
import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.response.HttpResponse;
import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.dto.AddressParam.AddressRequest;
import com.delivery.user.web.dto.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.web.dto.UserRegisterParam.UserRegisterRequest;
import com.delivery.utility.address.Address;


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
    
    public ResponseEntity<?> register(UserRegisterRequest dto) {
        userManagementService.register(dto.toParam());
        return ResponseEntity.status(CREATED).build();
    }
    
    public void login(UserLoginRequest loginDto, HttpSession session) {
        Authentication auth = userManagementService.login(loginDto.toParam());
        session.setAttribute(Authentication.KEY, auth);
    }
    
    public ResponseEntity<?> deleteAccount(Long id, DeleteAccountRequest dto, HttpSession session) {
        userManagementService.deleteAccount(dto.toParam(id));
        session.invalidate();
        return ResponseEntity.status(NO_CONTENT).build();
    }
    
    public void updateAccount(Long id, UpdateAccountRequest dto) {
        userManagementService.updateAccount(dto.toParam(id));
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> addAddress(Long id, AddressRequest addressRequest) {
        Address address = userManagementService.addAddress(addressRequest.toParam(id));
        return ResponseEntity.status(CREATED).body(response(address));
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> setMainAddress(Long id, Long addressId) {
        boolean isSet = userManagementService.setMainAddress(id, addressId);
        if (isSet) {
            return ResponseEntity.ok(response(SUCCESS));
        }
        return ResponseEntity.badRequest().body(response(FAIL, "address id is wrong"));
    }
    
    @Override
    public ResponseEntity<?> removeAddress(Long id, Long addressId) {
        boolean removed = userManagementService.removeAddress(id, addressId);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
