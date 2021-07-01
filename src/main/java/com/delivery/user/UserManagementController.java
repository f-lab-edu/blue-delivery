package com.delivery.user;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.user.DeleteAccountParam.DeleteAccountRequest;


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
    
    @InitBinder("userRegisterDto")
    void initRegisterPasswordValidator(WebDataBinder binder) {
        binder.addValidators(userRegisterPasswordValidator);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto dto) {
        userManagementService.register(dto);
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
    
    @PostMapping("/update")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody UserUpdateAccountDto dto) {
        userManagementService.updateAccount(dto);
        return ResponseEntity.status(HttpStatus.OK).body("User account update.");
    }
    
}
