package com.delivery.shop.menu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MenuOptionControllerImpl.class)
class MenuOptionGroupControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MenuOptionServiceImpl menuOptionGroupService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void registerMenuOptionGroupTest() throws Exception {

        MenuOptionGroup request = new MenuOptionGroup();
        request.setId(1L);
        request.setMenuId(1L);
        request.setName("맛선택");
        request.setOptionRequired(true);
        request.setMinimumOption(1);
        request.setMaximumOption(3);

        mockMvc.perform(post("/1/option-groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

}
