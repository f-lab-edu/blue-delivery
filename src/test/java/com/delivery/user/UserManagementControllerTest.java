package com.delivery.user;

import static com.delivery.user.UpdateAccountParam.*;
import static com.delivery.user.UserRegisterParam.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.delivery.user.DeleteAccountParam.DeleteAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest({UserManagementController.class, UserRegisterPasswordValidator.class})
class UserManagementControllerTest {
    
    @MockBean
    UserManagementService service;
    @Autowired
    UserRegisterPasswordValidator validator;
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    String registerUrl = "/users";
    
    String deleteAccountUrl = "/users/1";
    
    String userUpdateUrl = "/users/1";
    
    @Test
    public void userUpdateTest() throws Exception {
        String email = "email@email.com";
        String password = "P@ssw0rd";
        UserRegisterRequest user = new UserRegisterRequest(
                email,
                "testName1",
                "010-1111-1111",
                password,
                password,
                LocalDate.of(2000, Month.APRIL, 1),
                "address"
        );
        service.register(user.toParam());
        UpdateAccountRequest dto = new UpdateAccountRequest(
                email,
                "testName2",
                "010-2222-2222",
                password,
                "address"
        );
        
        String body;
        body = objectMapper.writeValueAsString(dto);
        
        mockMvc.perform(put(userUpdateUrl)
                .content(body).contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Test
    void deletingAccountInvalidateSessionTest() throws Exception {
        DeleteAccountRequest dto = new DeleteAccountRequest("nothing@email.com", "P@ssw0rd!");
        MvcResult mvcResult = sendDeleteAccountRequest(dto, status().isNoContent());
        
        HttpSession session = mvcResult.getRequest().getSession();
        assertThat(session.getAttribute("auth")).isNull();
    }
    
    private MvcResult sendDeleteAccountRequest(DeleteAccountRequest dto, ResultMatcher status) throws Exception {
        String body = objectMapper.writeValueAsString(dto);
        return mockMvc.perform(delete(deleteAccountUrl)
                .sessionAttr("auth", new Authentication("email", "nickname", "phone"))
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status)
                .andReturn();
    }
    
    @Test
    void registerPasswordValidatorTest() throws Exception {
        UserRegisterRequest wrongPassword = new UserRegisterRequest(
                "blue@email.com",
                "hello",
                "010-1234-4311",
                "P@ssw0rd!",
                "P@ssw0rd#",
                LocalDate.of(2000, Month.APRIL, 1),
                "address"
        );
        MvcResult mvcResult = sendRegisterRequest(wrongPassword, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).contains("errorLength=" + 1);
    }
    
    private MvcResult sendRegisterRequest(UserRegisterRequest dto, ResultMatcher status) throws Exception {
        String body;
        body = objectMapper.writeValueAsString(dto);
        return mockMvc.perform(post(registerUrl)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status)
                .andReturn();
    }
    
}
