package com.bluedelivery.api.authentication.adapter;

import static com.bluedelivery.common.response.ErrorCode.ALREADY_LOGGED_IN;
import static com.bluedelivery.common.response.ErrorCode.USER_NOT_FOUND;
import static com.bluedelivery.common.response.HttpResponse.SUCCESS;
import static com.bluedelivery.common.response.HttpResponse.response;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.api.authentication.AuthenticationController;
import com.bluedelivery.api.authentication.LoginRequest;
import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.authentication.Authentication;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {
    
    private final AuthenticationService authService;
  
    @Override
    public ResponseEntity<HttpResponse<Authentication>> login(Authentication loggedIn, LoginRequest dto) {
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
