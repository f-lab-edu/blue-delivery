package com.delivery.shop.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryManagerController.class)
class CategoryManagerControllerImplTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    CategoryManagerService categoryManagerService;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    void getAllCategories() throws Exception {
        List<Category> categories = List.of(new Category("KOREAN"),
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
