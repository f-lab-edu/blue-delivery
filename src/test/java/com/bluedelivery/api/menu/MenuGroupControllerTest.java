package com.bluedelivery.api.menu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bluedelivery.api.menu.adapter.MenuGroupController;
import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.application.shop.adapter.MenuGroupServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MenuGroupController.class)
public class MenuGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AuthenticationService authenticationService;

    @MockBean
    MenuGroupServiceImpl service;

    @Test
    @DisplayName("메뉴 그룹 생성시 201 CREATE 응답")
    public void registerMenuGroupTest() throws Exception {
        RegisterMenuGroupDto request = new RegisterMenuGroupDto();
        request.setShopId(1L);
        request.setName("사이드메뉴");
        request.setContent("5000원");

        mockMvc.perform(post("/shops/1/menu-groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }
}
