package com.delivery.user;

import static com.delivery.user.UpdateAccountParam.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.user.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.UserRegisterParam.UserRegisterRequest;


@RestController
@RequestMapping("/users")
public class UserManagementController {
    
    private UserManagementService userManagementService;
    
    private UserRegisterPasswordValidator userRegisterPasswordValidator;
    
    public UserManagementController(UserManagementService userManagementService,
                                    UserRegisterPasswordValidator userRegisterPasswordValidator) {
        this.userManagementService = userManagementService;
        this.userRegisterPasswordValidator = userRegisterPasswordValidator;
    }
    
    @InitBinder("userRegisterRequest")
    void initRegisterPasswordValidator(WebDataBinder binder) {
        binder.addValidators(userRegisterPasswordValidator);
    }
    
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest dto) {
        userManagementService.register(dto.toParam());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PostMapping("/login")
    public void login(@RequestBody UserLoginDto loginDto, HttpServletRequest request) {
        
        User user = userManagementService.login(loginDto);
        HttpSession httpSession = request.getSession();
        
        if (user.checkPasswordEquality(loginDto.getUserPassword())) {
            Authentication auth = new Authentication(
                    user.getEmail(),
                    user.getNickname(),
                    user.getPhone()
            );
            httpSession.setAttribute("auth", auth);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id,
                                           @Valid @RequestBody DeleteAccountRequest dto, HttpSession session) {
        userManagementService.deleteAccount(dto.toParam(id));
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") Long id,
                                           @Valid @RequestBody UpdateAccountRequest dto) {
        userManagementService.updateAccount(dto.toParam(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
