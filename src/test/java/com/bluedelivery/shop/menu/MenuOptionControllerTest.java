package com.bluedelivery.shop.menu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bluedelivery.application.authentication.AuthenticationService;
import com.bluedelivery.domain.authentication.AuthenticationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MenuOptionControllerImpl.class)
class MenuOptionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    MenuOptionServiceImpl menuOptionGroupService;
    
    @MockBean
    AuthenticationRepository authenticationRepository;
    
    @MockBean
    AuthenticationService authService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("메뉴 옵션 그룹 생성시 201 create 응답")
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
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("옵션 생성시 201 create 응답")
    public void registerMenuOptionTest() throws Exception {
        MenuOptionDto request = new MenuOptionDto();
        request.setOptionGroupId(1L);
        request.setName("매운맛");
        request.setPrice(0);

        mockMvc.perform(post("/1/options")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
    
}
