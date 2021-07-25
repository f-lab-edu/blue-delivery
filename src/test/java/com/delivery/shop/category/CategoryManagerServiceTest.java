package com.delivery.shop.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryManagerServiceTest {
    
    @Mock
    private CategoryRepository repository;
    private CategoryManagerService service;
    
    @BeforeEach
    void setup() {
        service = new CategoryManagerServiceHttp(repository);
    }
    
    @Test
    @DisplayName("카테고리 종류를 하나 추가하고 성공하면 카테고리 객체를 반환한다.")
    void addCategoryReturnEntity() {
        //given
        CreateCategoryParam target = new CreateCategoryParam("chicken");
        Category expected = new Category(target.getName());
        when(repository.addCategory(expected)).thenReturn(expected);
        
        //when
        Category category = service.addCategory(target);
        
        //then
        assertThat(category).isNotNull().isEqualTo(category);
    }
}
