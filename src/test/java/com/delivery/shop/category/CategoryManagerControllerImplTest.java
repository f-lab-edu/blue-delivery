package com.delivery.shop.category;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.delivery.shop.category.CreateCategoryParam.CreateCategoryRequest;
import com.delivery.shop.category.EditCategoryParam.EditCategoryRequest;
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
    @DisplayName("모든 종류의 카테고리를 가져오는 요청을 보내고 성공하면 상태코드 200과 카테고리 목록을 반환한다.")
    void getAllCategoriesTest() throws Exception {
        //given
        List<Category> categories = List.of(
                new Category("KOREAN"),
                new Category("CHICKEN"),
                new Category("PIZZA"));
        when(categoryManagerService.getAllCategories()).thenReturn(categories);
        //when
        //then
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", containsStringIgnoringCase("success")))
                .andExpect(jsonPath("$.data[0].name", containsString("KOREAN")))
                .andExpect(jsonPath("$.data[1].name", containsString("CHICKEN")))
                .andExpect(jsonPath("$.data[2].name", containsString("PIZZA")));
    }
    
    @Test
    @DisplayName("카테고리 종류 추가 요청을 보내고 성공하면 상태코드 201과 추가된 카테고리 정보를 반환한다.")
    void createCategoryTest() throws Exception {
        //given
        String chicken = "CHICKEN";
        CreateCategoryRequest request = new CreateCategoryRequest(chicken);
        Category category = new Category(request.getName());
        when(categoryManagerService.addCategory(request.toParam())).thenReturn(category);
        //when
        //then
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result", containsStringIgnoringCase("success")))
                .andExpect(jsonPath("$.data.name", containsString(chicken)));
    }
    
    @Test
    @DisplayName("카테고리 종류 추가 요청을 보내고 이미 존재하면 상태코드 409를 반환한다.")
    void failWhenDuplicatedCategoryAddedTest() throws Exception {
        //given
        CreateCategoryRequest request = new CreateCategoryRequest("CHICKEN");
        when(categoryManagerService.addCategory(request.toParam())).thenThrow(DuplicateKeyException.class);
        //when
        //then
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.result", containsStringIgnoringCase("fail")))
                .andExpect(jsonPath("$.message", containsStringIgnoringCase("already exist")));
    }
    
    @Test
    @DisplayName("카테고리 수정 요청을 보내고 성공하면 상태코드 200을 반환한다.")
    void updateCategoryTest() throws Exception {
        //given
        EditCategoryRequest request = new EditCategoryRequest("CHICKEN");
        //when
        //then
        mockMvc.perform(patch("/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(jsonPath("$.result", containsStringIgnoringCase("success")))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("카테고리 종류 삭제 요청을 보내고 성공하면 상태코드 204를 반환한다.")
    void deleteCategoryTest() throws Exception {
        //given
        when(categoryManagerService.deleteCategory(11L)).thenReturn(1L);
        //when
        //then
        mockMvc.perform(delete("/categories/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.result", containsStringIgnoringCase("success")));
    }
    
    @Test
    @DisplayName("카테고리 종류 삭제 요청을 보내고 존재하지 않는 카테고리면 상태코드 404를 반환한다.")
    void failWhenNothingRemoved() throws Exception {
        //given
        when(categoryManagerService.deleteCategory(11L)).thenReturn(0L);
        //when
        //then
        mockMvc.perform(delete("/categories/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.result", containsStringIgnoringCase("fail")))
                .andExpect(jsonPath("$.message", containsStringIgnoringCase("not found")));
    }
    
    
}
