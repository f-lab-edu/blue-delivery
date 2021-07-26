package com.bluedelivery.user.web;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluedelivery.authentication.Authentication;
import com.bluedelivery.authentication.AuthenticationService;
import com.bluedelivery.config.resolver.AuthenticatedUserArgumentResolver;
import com.bluedelivery.user.web.dto.UserLoginParam.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({MockitoExtension.class})
public class AuthenticationControllerLoginTest {
    
    private MockMvc mvc;
    
    @Mock
    AuthenticationService authService;
    
    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(new AuthenticationControllerImpl(authService))
                .setCustomArgumentResolvers(new AuthenticatedUserArgumentResolver(authService))
                .alwaysDo(print())
                .build();
    }
    
    @Test
    @DisplayName("성공적으로 로그인하면 Authentication 객체를 반환한다")
    void returnCustomSessionIfLoginSuccess() throws Exception {
        //given
        UserLoginRequest dto = new UserLoginRequest("email", "password");
        Authentication authentication = new Authentication("t0ken", 1L);
        when(authService.authenticate(dto.toParam())).thenReturn(authentication);
        
        //when
        ResultActions action = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)));
        
        //then
        action.andExpect(status().isOk())
                .andExpect(content().string(containsString("token")))
                .andExpect(jsonPath("$.data.token", is("t0ken")));
    }
}
