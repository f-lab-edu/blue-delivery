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

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
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

    String url = "/members/register";

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
        sendRequest(dto, status().isOk());
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
        MvcResult mvcResult = sendRequest(wrongPassword, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).contains("errorLength=" + 1);
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
        MvcResult mvcResult = sendRequest(wrongEmail, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).contains("errorLength=" + 7);

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
        MvcResult mvcResult = sendRequest(wrongEmail, status().isBadRequest());
        String responseBody = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).contains("errorLength=" + 6);

    }

    private MvcResult sendRequest(UserRegisterDto dto, ResultMatcher status) throws Exception {
        String body;
        body = objectMapper.writeValueAsString(dto);
        return mockMvc.perform(post(url)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status)
                .andReturn();
    }

}