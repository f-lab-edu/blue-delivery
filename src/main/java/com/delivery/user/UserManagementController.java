package com.delivery.user;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/members")
public class UserManagementController {

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegisterDto dto) {

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("errorLength=" + ex.getFieldErrors().size()+"\n");
        for (FieldError error : ex.getFieldErrors()) {
            sb.append(error.getField() + " : " + error.getDefaultMessage() +"\n");
        }
        return ResponseEntity.badRequest().body(sb.toString());
    }

}
