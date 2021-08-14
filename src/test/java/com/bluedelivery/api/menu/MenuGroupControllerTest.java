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

import com.bluedelivery.api.menu.adapter.MenuGroupControllerImpl;
import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.application.shop.adapter.MenuGroupServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MenuGroupControllerImpl.class)
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

    @Test
    @DisplayName("메뉴 그룹 수정시 200 OK 응답")
    public void updateMenuGroupTest() throws Exception {
        UpdateMenuGroupDto request = new UpdateMenuGroupDto();
        request.setId(1L);
        request.setShopId(1L);
        request.setName("사이드메뉴");
        request.setContent("5000원");

        mockMvc.perform(put("/shops/1/menu-groups/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("메뉴 그룹 삭제 성공시 204 NO CONFLICT 응답")
    public void deleteMenuGroupTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/shops/1/menu-groups/1")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(id)))
                .andExpect(status().isNoContent());

    }
}
