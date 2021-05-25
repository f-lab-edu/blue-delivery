package com.delivery.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegisterPasswordValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserRegisterDto.class);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterDto dto = (UserRegisterDto) target;
        if (dto.getPassword() != null && dto.getConfirmedPassword() != null) {
            if (!isPasswordEquals(dto)) {
                errors.rejectValue("password", "", "패스워드 불일치");
            }
        }
    }
    
    private boolean isPasswordEquals(UserRegisterDto dto) {
        return dto.getPassword().equals(dto.getConfirmedPassword());
    }
}
