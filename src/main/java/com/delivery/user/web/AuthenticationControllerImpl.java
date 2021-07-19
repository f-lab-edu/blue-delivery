package com.delivery.user.web;

import static com.delivery.response.HttpResponse.SUCCESS;
import static com.delivery.response.HttpResponse.response;
import static org.springframework.http.HttpStatus.CREATED;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.response.HttpResponse;
import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.dto.UserLoginParam.UserLoginRequest;
import com.delivery.user.web.dto.UserRegisterParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {
    
    private final UserManagementService userManagementService;
    private final PasswordValidator passwordValidator;
    
    @InitBinder("userRegisterRequest")
    void initRegisterPasswordValidator(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }
    
    
    public ResponseEntity<HttpResponse<?>> login(UserLoginRequest loginDto, HttpSession session) {
        Authentication auth = userManagementService.login(loginDto.toParam())
                .orElseThrow(() -> new ApiException(ErrorCode.INVALID_AUTHENTICATION));
        session.setAttribute(Authentication.KEY, auth);
        return ResponseEntity.ok(response(SUCCESS, "successfully logged in"));
    }
    
    public ResponseEntity<?> register(UserRegisterParam.UserRegisterRequest dto) {
        userManagementService.register(dto.toParam());
        return ResponseEntity.status(CREATED).build();
    }
    
}
