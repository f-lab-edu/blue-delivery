package com.delivery.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.utility.address.Address;

@ExtendWith(MockitoExtension.class)
class UserManagementServiceTest {
    
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserManagementService service = new UserManagementServiceHttp(userRepository);
    
    String email;
    String password;
    UserRegisterParam param;
    
    @BeforeEach
    void setup() {
        email = "myEmail@email.com";
        password = "P@ssw0rd!";
        param = new UserRegisterParam(
                email, "nickname", "010-1234-5676",
                password, LocalDate.now().minusDays(1), new Address());
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
