package com.delivery.user.web;

import static com.delivery.response.ErrorCode.ALREADY_LOGGED_IN;
import static com.delivery.response.ErrorCode.USER_NOT_FOUND;
import static com.delivery.response.HttpResponse.SUCCESS;
import static com.delivery.response.HttpResponse.response;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.authentication.Authentication;
import com.delivery.authentication.AuthenticationService;
import com.delivery.exception.ApiException;
import com.delivery.response.HttpResponse;
import com.delivery.user.web.dto.UserLoginParam.UserLoginRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {
    
    private final AuthenticationService authService;
  
    @Override
    public ResponseEntity<HttpResponse<Authentication>> login(Authentication loggedIn, UserLoginRequest dto) {
        if (loggedIn != null) {
            throw new ApiException(ALREADY_LOGGED_IN);
        }
        Authentication authentication = authService.authenticate(dto.toParam());
        return ResponseEntity.ok(response(SUCCESS, authentication));
    }
    
    @Override
    public ResponseEntity<HttpResponse<?>> logout(Authentication loggedIn) {
        if (loggedIn == null) {
            throw new ApiException(USER_NOT_FOUND);
        }
        loggedIn.invalidate();
        authService.expire(loggedIn);
        return ResponseEntity.status(NO_CONTENT).body(response("logout done"));
    }
    
}
