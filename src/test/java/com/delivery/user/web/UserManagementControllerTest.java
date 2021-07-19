package com.delivery.user.web;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.delivery.user.Authentication;
import com.delivery.user.application.UserManagementService;
import com.delivery.user.domain.UserRepository;
import com.delivery.user.web.dto.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.web.dto.UserRegisterParam.UserRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest({UserManagementController.class})
class UserManagementControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objMapper;
    
    @MockBean
    UserManagementService userManagementService;
    
    @MockBean
    UserRepository userRepository;
    String url = "/users";
    
    @Test
    @DisplayName("회원을 삭제하면 session에 있는 인증 정보도 사라진다.")
    void deletingAccountInvalidateSessionTest() throws Exception {
        Authentication auth = new Authentication();
        auth.setId(1L);
        MvcResult result = mockMvc.perform(delete(url + "/1")
                .sessionAttr(Authentication.KEY, auth)
                .content(objMapper.writeValueAsString(new DeleteAccountRequest("nothing@email.com", "P@ssw0rd!")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        HttpSession session = result.getRequest().getSession();
        assertThat(session.getAttribute(Authentication.KEY)).isNull();
    }
   
}
