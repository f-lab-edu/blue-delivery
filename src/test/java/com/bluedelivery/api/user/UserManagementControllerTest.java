package com.bluedelivery.api.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluedelivery.api.user.UserManagementController;
import com.bluedelivery.api.user.UserRegisterRequest;
import com.bluedelivery.api.user.adapter.PasswordValidator;
import com.bluedelivery.api.user.adapter.UserManagementControllerImpl;
import com.bluedelivery.application.user.UserManagementService;
import com.bluedelivery.common.config.GlobalExceptionHandler;
import com.bluedelivery.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
class UserManagementControllerTest {
    
    MockMvc mockMvc;
    
    ObjectMapper objMapper;
    
    PasswordValidator validator;
    
    UserManagementController userManagementController;
    
    @Mock
    UserManagementService userManagementService;
    
    @BeforeEach
    void setup() {
        objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());
        validator = new PasswordValidator();
        userManagementController = new UserManagementControllerImpl(userManagementService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(userManagementController)
                .setValidator(validator)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();
    }
    
    @Test
    @DisplayName("회원가입시 1, 2차 비밀번호가 일치하지 않으면 400 응답")
    void checkPasswordValidation() throws Exception {
        String password = "P@ssw0rd!";
        MvcResult mvcResult = mockMvc.perform(post("/users")
                        .content(objMapper.writeValueAsString(
                                new UserRegisterRequest("nothing@email.com", "nickname", "010-1234-1234",
                                        password, password + "wrong",
                                        LocalDate.of(2020, Month.MAY, 1))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        Boolean result = mvcResult.getResponse().getContentAsString().contains("fail");
        assertThat(result).isTrue();
    }
    
    @Test
    @DisplayName("회원가입시 (다른 조건들을 만족하고) 1, 2차 비밀번호가 일치해야 201 created 응답")
    void checkPasswordNotValidated() throws Exception {
        //given
        String email = "nothing@email.com";
        String nickname = "nickname";
        String phone = "010-1234-1234";
        String password = "P@ssw0rd!";
        LocalDate birth = LocalDate.of(2020, Month.MAY, 1);
        UserRegisterRequest dto = new UserRegisterRequest(email, nickname, phone, password, password, birth);
        given(userManagementService.register(dto.toParam())).willReturn(dto.toParam().toEntity());
        //when
        ResultActions perform = mockMvc.perform(post("/users")
                .content(objMapper.writeValueAsString(
                        dto))
                .contentType(MediaType.APPLICATION_JSON));
        //then
        perform
                .andExpect(status().isCreated())
                .andReturn();
    }
    
}
