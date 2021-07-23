package com.delivery.user.web;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.delivery.config.interceptor.SessionRepository;
import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.dto.UserLoginParam.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({MockitoExtension.class})
public class AuthenticationControllerLoginTest {
    
    private MockMvc mvc;
    
    @Mock
    UserManagementService service;
    
    @Mock
    PasswordValidator valida;
    
    @Mock
    SessionRepository sessionRepository;
    
    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new AuthenticationControllerImpl(service, valida, sessionRepository))
                .alwaysDo(print())
                .build();
    }
    
    @Test
    @DisplayName("성공적으로 로그인하면 CustomSession을 반환한다")
    void returnCustomSessionIfLoginSuccess() throws Exception {
        UserLoginRequest dto = new UserLoginRequest("email", "password");
        Authentication authentication = new Authentication();
        authentication.setId(1L);
        when(service.login(dto.toParam())).thenReturn(Optional.of(authentication));
        mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("sessionId")))
                .andExpect(jsonPath("$.data.authentication.id", is(1) ));
    }
}
