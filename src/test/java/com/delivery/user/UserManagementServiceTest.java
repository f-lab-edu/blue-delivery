package com.delivery.user;

import static com.delivery.user.UserRegisterParam.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.delivery.exception.ApiException;
import com.delivery.exception.ExceptionEnum;

@ExtendWith(MockitoExtension.class)
class UserManagementServiceTest {
    
    @Mock UserRepository userRepository;
    @InjectMocks UserManagementService service;
    
    String email = "myEmail@email.com";
    String password = "P@ssw0rd!";
    UserRegisterParam dto = new UserRegisterParam(
            email,
            "nickname",
            "010-1234-5676",
            password,
            LocalDate.now().minusDays(1),
            "address"
    );
    
    
    @Test
    @DisplayName("회원가입시 중복 이메일인 경우 DUPLICATE_EMAIL 예외 발생")
    void registerDuplicateEmailTest() {
        doThrow(new DuplicateKeyException("for key ~ email")).when(userRepository).save(dto.toEntity());
        ExceptionEnum error = assertThrows(ApiException.class, () -> service.register(dto)).getError();
        assertThat(error).isEqualTo(ExceptionEnum.DUPLICATE_EMAIL);
    }
    
    @Test
    @DisplayName("회원가입시 중복 닉네임인 경우 DUPLICATE_NICKNAME 예외 발생")
    void registerDuplicateNicknameTest() {
        doThrow(new DuplicateKeyException("for key ~ nickname")).when(userRepository).save(dto.toEntity());
        ExceptionEnum error = assertThrows(ApiException.class, () -> service.register(dto)).getError();
        assertThat(error).isEqualTo(ExceptionEnum.DUPLICATE_NICKNAME);
    }
    
    @Test
    @DisplayName("회원가입시 중복 전화번호인 경우 DUPLICATE_PHONE 예외 발생")
    void registerDuplicatePhoneTest() {
        doThrow(new DuplicateKeyException("for key ~ phone")).when(userRepository).save(dto.toEntity());
        ExceptionEnum error = assertThrows(ApiException.class, () -> service.register(dto)).getError();
        assertThat(error).isEqualTo(ExceptionEnum.DUPLICATE_PHONE);
    }
    
}
