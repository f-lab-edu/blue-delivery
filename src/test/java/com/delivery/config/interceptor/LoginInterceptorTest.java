package com.delivery.config.interceptor;

import static com.delivery.config.CustomSession.ID_HEADER_NAME;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.delivery.config.CustomSession;
import com.delivery.config.GlobalExceptionHandler;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.AuthenticationController;
import com.delivery.user.web.AuthenticationControllerImpl;
import com.delivery.user.web.PasswordValidator;
import com.delivery.user.web.dto.UserLoginParam.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class LoginInterceptorTest {
    
    private MockMvc mvc;
    private AuthenticationController authenticationController;
    
    @Mock
    private SessionRepository sessionRepository;
    
    @Mock
    private UserManagementService service;
    
    @Mock
    private PasswordValidator passwordValidator;
    
    @BeforeEach
    void setup() {
        authenticationController = new AuthenticationControllerImpl(service, passwordValidator, sessionRepository);
        mvc = MockMvcBuilders.standaloneSetup(authenticationController)
                .addInterceptors(new LoginInterceptor(sessionRepository))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }
    
    @Test
    void throwExceptionIfAlreadyLoggedIn() throws Exception {
        String sessionId = "mysessionid";
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(new CustomSession()));
        mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new UserLoginRequest()))
                .header(ID_HEADER_NAME, sessionId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsStringIgnoringCase("already logged in")));
    }
}
