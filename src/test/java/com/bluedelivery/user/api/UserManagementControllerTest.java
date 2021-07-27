package com.bluedelivery.user.api;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluedelivery.api.user.UserManagementController;
import com.bluedelivery.api.user.UserRegisterRequest;
import com.bluedelivery.api.user.adapter.UserManagementControllerImpl;
import com.bluedelivery.application.user.UserManagementService;
import com.bluedelivery.config.GlobalExceptionHandler;
import com.bluedelivery.infra.user.PasswordValidator;
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
        String password = "P@ssw0rd!";
        mockMvc.perform(post("/users")
                .content(objMapper.writeValueAsString(
                        new UserRegisterRequest("nothing@email.com", "nickname", "010-1234-1234",
                                password, password,
                                LocalDate.of(2020, Month.MAY, 1))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
    
}
