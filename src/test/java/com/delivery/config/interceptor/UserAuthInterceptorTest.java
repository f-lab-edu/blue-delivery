package com.delivery.config.interceptor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.web.PasswordValidator;
import com.delivery.user.web.UserManagementController;

@WebMvcTest(controllers = UserManagementController.class)
class UserAuthInterceptorTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserManagementService service;
    
    @MockBean
    private PasswordValidator validator;
    
    @Test
    @DisplayName("인증객체의 ID와 URL의 타겟 ID가 일치하면 정상적으로 처리한다.")
    void successWhenEverythingIsOk() throws Exception {
        Authentication auth = new Authentication();
        auth.setId(1L);
        mockMvc.perform(get("/users/1").sessionAttr(Authentication.KEY, auth))
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
        mockMvc.perform(get("/users/1").sessionAttr(Authentication.KEY, auth))
                .andExpect(status().isForbidden());
    }
}
