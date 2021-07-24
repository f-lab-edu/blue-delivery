package com.delivery.config.interceptor;

import static com.delivery.user.Authentication.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.delivery.config.CustomSession;
import com.delivery.config.GlobalExceptionHandler;
import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.UserManagementController;
import com.delivery.user.web.UserManagementControllerImpl;

@ExtendWith(MockitoExtension.class)
class UserAuthInterceptorTest {
    
    private MockMvc mockMvc;
    private CustomSession session;
    private Authentication auth;
    
    private UserManagementController userManagementController;
    
    @Mock
    private UserManagementService userManagementService;
    
    @Mock
    private SessionRepository sessionRepository;
    
    @BeforeEach
    void setup() {
        userManagementController = new UserManagementControllerImpl(userManagementService);
        auth = new Authentication();
        session = new CustomSession();
        session.setAuthentication(auth);
        session.setMaxInactiveTime(Duration.ofDays(-1L));
        
        mockMvc = MockMvcBuilders.standaloneSetup(userManagementController)
                .addInterceptors(new UserAuthInterceptor(sessionRepository))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
      
    }
    
    @Test
    @DisplayName("인증객체의 ID와 URL의 타겟 ID가 일치하면 정상적으로 처리한다.")
    void successWhenEverythingIsOk() throws Exception {
        when(sessionRepository.findById("mysessionid")).thenReturn(Optional.of(session));
    
        auth.setId(1L);
        mockMvc.perform(get("/users/1")
                .header(AUTHORIZATION, "mysessionid"))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("인증객체가 없으면 ApiException 발생")
    void throwApiExceptionWhenThereIsNoAuthentication() throws Exception {
        mockMvc.perform(get("/users/1")).andExpect(status().isForbidden());
    }
    
    @Test
    @DisplayName("인증객체와 요청 URL의 타겟이 다르면 ApiException 발생")
    void throwApiExceptionWhenThereIsWrongAuthentication() throws Exception {
        Authentication auth = new Authentication();
        auth.setId(100L);
        mockMvc.perform(get("/users/1").sessionAttr(AUTH_VALUE, auth))
                .andExpect(status().isForbidden());
    }
}
