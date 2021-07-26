package com.bluedelivery.user.web;

import static com.bluedelivery.response.ErrorCode.ALREADY_LOGGED_IN;
import static com.bluedelivery.response.ErrorCode.USER_NOT_FOUND;
import static com.bluedelivery.response.HttpResponse.SUCCESS;
import static com.bluedelivery.response.HttpResponse.response;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.authentication.Authentication;
import com.bluedelivery.authentication.AuthenticationService;
import com.bluedelivery.exception.ApiException;
import com.bluedelivery.response.HttpResponse;
import com.bluedelivery.user.web.dto.UserLoginParam.UserLoginRequest;

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
