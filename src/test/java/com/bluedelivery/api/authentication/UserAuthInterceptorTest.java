package com.bluedelivery.api.authentication;

import static com.bluedelivery.application.authentication.AuthenticationService.BEARER_PREFIX;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.api.authentication.AuthenticationRequired;
import com.bluedelivery.api.authentication.UserAuthInterceptor;
import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.common.config.GlobalExceptionHandler;
import com.bluedelivery.domain.authentication.Authentication;

@ExtendWith(MockitoExtension.class)
class UserAuthInterceptorTest {
    
    private MockMvc mockMvc;
    
    private TestController testController;
    
    @Mock
    private AuthenticationService authService;
    
    @BeforeEach
    void setup() {
        testController = new TestController();
        mockMvc = MockMvcBuilders.standaloneSetup(testController)
                .addInterceptors(new UserAuthInterceptor(authService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();
    }
    
    @Test
    @DisplayName("인증객체의 ID와 URL의 타겟 ID가 일치하면 정상적으로 처리한다.")
    void successWhenEverythingIsOk() throws Exception {
        //given
        Authentication auth = new Authentication("t0ken", 1L);
        when(authService.getAuthentication(BEARER_PREFIX + "t0ken")).thenReturn(Optional.of(auth));
        //when
        ResultActions perform = mockMvc.perform(get("/{id}", 1)
                .header(AUTHORIZATION, BEARER_PREFIX + "t0ken"));
        //then
        perform.andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("인증객체가 없으면 ApiException 발생")
    void throwApiExceptionWhenThereIsNoAuthentication() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(get("/{id}", 1));
        //then
        perform.andExpect(status().isUnauthorized());
    }
    
    @Test
    @DisplayName("인증객체와 요청 URL의 타겟이 다르면 ApiException 발생")
    void throwApiExceptionWhenThereIsWrongAuthentication() throws Exception {
        //given
        Authentication auth = new Authentication("t0ken", 2L);
        String authenticationHeader = BEARER_PREFIX + "t0ken";
        when(authService.getAuthentication(authenticationHeader)).thenReturn(Optional.of(auth));
        //when
        ResultActions perform = mockMvc.perform(get("/{id}", 1).header(AUTHORIZATION, authenticationHeader));
        //then
        perform.andExpect(status().isForbidden());
    }
    
    @AuthenticationRequired
    @RestController
    public static class TestController {
        @GetMapping("/{id}")
        public void testMethod() {
        
        }
    }
}
