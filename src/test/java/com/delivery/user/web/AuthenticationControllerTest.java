package com.delivery.user.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.delivery.config.interceptor.SessionRepository;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.domain.UserRepository;
import com.delivery.user.web.dto.UserRegisterParam;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest({AuthenticationController.class, PasswordValidator.class})
class AuthenticationControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objMapper;
    
    @Autowired
    PasswordValidator validator;
    
    @MockBean
    UserManagementService userManagementService;
    
    @MockBean
    SessionRepository sessionRepository;
    
    @MockBean
    UserRepository userRepository;
    String baseUrl = "/auth";
    
    @Test
    @DisplayName("회원가입시 1, 2차 비밀번호가 일치하지 않으면 400 응답")
    void checkPasswordValidation() throws Exception {
        String password = "P@ssw0rd!";
        MvcResult mvcResult = mockMvc.perform(post(baseUrl + "/register")
                .content(objMapper.writeValueAsString(
                        new UserRegisterParam.UserRegisterRequest("nothing@email.com", "nickname", "010-1234-1234",
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
        mockMvc.perform(post(baseUrl + "/register")
                .content(objMapper.writeValueAsString(
                        new UserRegisterParam.UserRegisterRequest("nothing@email.com", "nickname", "010-1234-1234",
                                password, password,
                                LocalDate.of(2020, Month.MAY, 1))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
    
}
