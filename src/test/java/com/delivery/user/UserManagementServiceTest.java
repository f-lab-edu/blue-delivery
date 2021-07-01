package com.delivery.user;

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
    UserRegisterDto dto = new UserRegisterDto(
            email,
            "nickname",
            "010-1234-5676",
            password,
            password,
            LocalDate.now().minusDays(1)
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
    
    @Test
    @DisplayName("id에 해당하는 고객정보가 없으면 예외 발생")
    void throwExceptionIfUserIdIsNotExist() {
        ExceptionEnum error = assertThrows(ApiException.class, () -> service.findUserById(1L)).getError();
        assertThat(error).isEqualTo(ExceptionEnum.USER_NOT_FOUND);
    }
    
    @Test
    @Disabled
    void userUpdateTest() {
        UserRegisterDto user = new UserRegisterDto(
                "test1",
                "testName1",
                "010-1111-1111",
                "1234",
                "1234",
                LocalDate.of(2030, Month.APRIL, 1));
        service.register(user);
        
        UserUpdateAccountDto dto = new UserUpdateAccountDto(
                "test1",
                "testName2",
                "010-2222-2222",
                "1234",
                LocalDate.of(2030, Month.APRIL, 1)
        );
        service.updateAccount(dto);
        
//        User findUser = service.getAccount(user.getEmail());
//
//        assertThat(user.getNickname()).isNotEqualTo(findUser.getNickname());
    }
    
    
}
