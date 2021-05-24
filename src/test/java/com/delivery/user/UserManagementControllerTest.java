package com.delivery.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
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

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({UserManagementController.class, UserRegisterPasswordValidator.class})
class UserManagementControllerTest {

    @MockBean
    UserManagementService service;

    @Autowired
    UserRegisterPasswordValidator v;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String registerUrl = "/members/register";
    String deleteAccountUrl = "/members/";


    @Test
    void deletingAccountInvalidateSessionTest() throws Exception {
        DeleteAccountDto dto = new DeleteAccountDto("nothing@email.com", "P@ssw0rd!");
        MvcResult mvcResult = sendDeleteAccountRequest(dto, status().isNoContent());

        HttpSession session = mvcResult.getRequest().getSession();
        assertThat(session.getAttribute("login")).isNull();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).contains("User account deleted.");
    }

    private MvcResult sendDeleteAccountRequest(DeleteAccountDto dto, ResultMatcher status) throws Exception {
        String body = objectMapper.writeValueAsString(dto);
        return mockMvc.perform(delete(deleteAccountUrl)
                .sessionAttr("login", new UserLoginDto(dto.getEmail(), dto.getPassword()))
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status)
                .andReturn();
    }

    @Test
    void validationTest() throws Exception {
        UserRegisterDto dto = new UserRegisterDto(
                "blue@gmail.com",
                "chicken",
                "010-1234-4321",
                "myP@ssw0rd",
                "myP@ssw0rd",
                LocalDate.of(2000, Month.APRIL, 1)
        );
        sendRegisterRequest(dto, status().isCreated());
    }

    @Test
    void registerPasswordValidatorTest() throws Exception {
        UserRegisterDto wrongPassword = new UserRegisterDto(
                "blue@email.com",
                "hello",
                "010-1234-4311",
                "P@ssw0rd!",
                "P@ssw0rd#",
                LocalDate.of(2000, Month.APRIL, 1)
        );
        MvcResult mvcResult = sendRegisterRequest(wrongPassword, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).contains("errorLength=" + 1);
    }

    @Test
    void validationFailTest() throws Exception {
        UserRegisterDto wrongEmail = new UserRegisterDto(
                "blue",
                "",
                "020-1234-4321",
                "mypassword",
                "mypassword2",
                LocalDate.of(2030, Month.APRIL, 1)
        );
        MvcResult mvcResult = sendRegisterRequest(wrongEmail, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).contains("errorLength=" + 7);

    }

    @Test
    void notNullTest() throws Exception {
        UserRegisterDto wrongEmail = new UserRegisterDto(
                null,
                null,
                null,
                null,
                null,
                null
        );
        MvcResult mvcResult = sendRegisterRequest(wrongEmail, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        assertThat(responseBody).contains("errorLength=" + 6);

    }

    private MvcResult sendRegisterRequest(UserRegisterDto dto, ResultMatcher status) throws Exception {
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
