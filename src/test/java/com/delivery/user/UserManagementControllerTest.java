package com.delivery.user;

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

import com.delivery.user.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.UserRegisterParam.UserRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest({UserManagementController.class, PasswordValidator.class})
class UserManagementControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objMapper;
    @Autowired
    PasswordValidator validator;
    @MockBean
    UserManagementService userManagementService;
    @MockBean
    UserRepository userRepository;
    
    String url = "/users";
    
    @Test
    @DisplayName("회원을 삭제하면 session에 있는 인증 정보도 사라진다.")
    void deletingAccountInvalidateSessionTest() throws Exception {
        MvcResult result = mockMvc.perform(delete(url + "/1")
                .sessionAttr(Authentication.KEY, new Authentication())
                .content(objMapper.writeValueAsString(new DeleteAccountRequest("nothing@email.com", "P@ssw0rd!")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        HttpSession session = result.getRequest().getSession();
        assertThat(session.getAttribute(Authentication.KEY)).isNull();
    }
    
    @Test
    @DisplayName("회원가입시 1, 2차 비밀번호가 일치하지 않으면 400 응답")
    void checkPasswordValidation() throws Exception {
        String password = "P@ssw0rd!";
        MvcResult mvcResult = mockMvc.perform(post(url)
                .content(objMapper.writeValueAsString(
                        new UserRegisterRequest("nothing@email.com", "nickname", "010-1234-1234",
                                password, password + "wrong",
                                LocalDate.of(2020, Month.MAY, 1), "seoul")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        boolean result = mvcResult.getResponse().getContentAsString().contains("패스워드 불일치");
        assertThat(result).isTrue();
    }
    
    @Test
    @DisplayName("회원가입시 (다른 조건들을 만족하고) 1, 2차 비밀번호가 일치해야 201 created 응답")
    void checkPasswordNotValidated() throws Exception {
        String password = "P@ssw0rd!";
        mockMvc.perform(post(url)
                .content(objMapper.writeValueAsString(
                        new UserRegisterRequest("nothing@email.com", "nickname", "010-1234-1234",
                                password, password,
                                LocalDate.of(2020, Month.MAY, 1), "seoul")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
    
}
