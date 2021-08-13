package com.bluedelivery.api.menu;

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

@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MenuService service;

    @MockBean
    AuthenticationService authenticationService;


    @Test
    public void deleteMenuTest() throws Exception {
        Long request = 1L;

        mockMvc.perform(delete("/menu-groups/1/menus/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(request)))
                .andExpect(status().isNoContent());

    }
}
