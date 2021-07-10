package com.delivery.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.application.UserManagementServiceHttp;
import com.delivery.user.domain.UserRepository;
import com.delivery.user.web.dto.UserRegisterParam;
import com.delivery.utility.address.domain.AddressService;

@ExtendWith(MockitoExtension.class)
class UserManagementServiceTest {
    
    @Mock
    UserRepository userRepository;
    @Mock
    AddressService addressService;
    UserManagementService service;
    
    String email;
    String password;
    UserRegisterParam param;
    
    @BeforeEach
    void setup() {
        service = new UserManagementServiceHttp(userRepository, addressService);
        email = "myEmail@email.com";
        password = "P@ssw0rd!";
        param = new UserRegisterParam(
                email, "nickname", "010-1234-5676",
                password, LocalDate.of(2000, Month.MAY, 1));
    }
    
    
    @Test
    @DisplayName("회원가입시 중복 이메일인 경우 DUPLICATE_EMAIL 예외 발생")
    void registerDuplicateEmailTest() {
        doThrow(new DuplicateKeyException("for key ~ email")).when(userRepository).save(param.toEntity());
        ErrorCode error = assertThrows(ApiException.class, () -> service.register(param)).getError();
        assertThat(error).isEqualTo(ErrorCode.DUPLICATE_EMAIL);
    }
    
    @Test
    @DisplayName("회원가입시 중복 닉네임인 경우 DUPLICATE_NICKNAME 예외 발생")
    void registerDuplicateNicknameTest() {
        doThrow(new DuplicateKeyException("for key ~ nickname")).when(userRepository).save(param.toEntity());
        ErrorCode error = assertThrows(ApiException.class, () -> service.register(param)).getError();
        assertThat(error).isEqualTo(ErrorCode.DUPLICATE_NICKNAME);
    }
    
    @Test
    @DisplayName("회원가입시 중복 전화번호인 경우 DUPLICATE_PHONE 예외 발생")
    void registerDuplicatePhoneTest() {
        doThrow(new DuplicateKeyException("for key ~ phone")).when(userRepository).save(param.toEntity());
        ErrorCode error = assertThrows(ApiException.class, () -> service.register(param)).getError();
        assertThat(error).isEqualTo(ErrorCode.DUPLICATE_PHONE);
    }
    
}
