package com.bluedelivery.api.menu;

import static com.bluedelivery.domain.menu.Menu.MenuStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bluedelivery.api.menu.adapter.MenuController;
import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.application.shop.adapter.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MenuService service;

    @MockBean
    AuthenticationService authenticationService;

    @Test
    public void registerMenuTest() throws Exception {
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setName("테스트 메뉴");
        dto.setPrice(3000);

        mockMvc.perform(post("/menu-groups/1/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void menuStatusUpdateTest() throws Exception {
        UpdateMenuDto dto = new UpdateMenuDto();
        dto.setStatus(HIDDEN);

        mockMvc.perform(patch("/menu-groups/1/menus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMenuTest() throws Exception {
        Long request = 1L;

        mockMvc.perform(delete("/menu-groups/1/menus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(request)))
                .andExpect(status().isNoContent());

    }
}
