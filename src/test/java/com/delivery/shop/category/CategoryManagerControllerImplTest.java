package com.delivery.shop.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class CategoryManagerControllerImplTest {
    
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    
    @Mock
    private CategoryManagerService categoryManagerService;
    
    @InjectMocks
    private CategoryManagerControllerImpl categoryManagerController;
    
    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(categoryManagerController)
                .build();
    }
    
    @Test
    void getAllCategories() throws Exception {
        List<Category> categories = List.of(
                new Category("KOREAN"),
                new Category("CHICKEN"),
                new Category("PIZZA"));
        when(categoryManagerService.getAllCategories()).thenReturn(categories);
        String expected = objectMapper.writeValueAsString(categories.stream()
                .map(x -> x.toResponse())
                .collect(Collectors.toList()));
    
        MvcResult mvcResult = mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertThat(response).contains(expected);
    }
}
