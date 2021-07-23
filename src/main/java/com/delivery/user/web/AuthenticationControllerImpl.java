package com.delivery.user.web;

import static com.delivery.config.CustomSession.SESSION_STR;
import static com.delivery.response.HttpResponse.SUCCESS;
import static com.delivery.response.HttpResponse.response;
import static org.springframework.http.HttpStatus.*;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.config.CustomSession;
import com.delivery.config.interceptor.SessionRepository;
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
    private final SessionRepository sessionRepository;
    
    @InitBinder("userRegisterRequest")
    void initRegisterPasswordValidator(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }
    
    
    public ResponseEntity<HttpResponse<CustomSession>> login(UserLoginRequest loginDto) {
        Authentication auth = userManagementService.login(loginDto.toParam())
                .orElseThrow(() -> new ApiException(ErrorCode.INVALID_AUTHENTICATION));
        CustomSession customSession = new CustomSession(UUID.randomUUID().toString(), auth);
        sessionRepository.save(customSession);
        return ResponseEntity.ok(response(SUCCESS, customSession));
    }
    
    public ResponseEntity<?> register(UserRegisterParam.UserRegisterRequest dto) {
        userManagementService.register(dto.toParam());
        return ResponseEntity.status(CREATED).build();
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> logout(HttpServletRequest request) {
        CustomSession session = (CustomSession) request.getAttribute(SESSION_STR);
        session.invalidate();
        return ResponseEntity.status(NO_CONTENT).body(response("logout done"));
    }
    
}
