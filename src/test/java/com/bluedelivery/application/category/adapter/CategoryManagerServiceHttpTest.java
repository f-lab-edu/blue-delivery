package com.bluedelivery.application.category.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bluedelivery.application.category.CategoryManagerService;
import com.bluedelivery.application.category.CategoryNotFoundException;
import com.bluedelivery.domain.category.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryManagerServiceHttpTest {
    @Mock
    private CategoryRepository repository;
    private CategoryManagerService service;
    
    @BeforeEach
    void setup() {
        service = new CategoryManagerServiceHttp(repository);
    }
    
    @Test
    void throw_exception_when_category_is_not_found() {
        //given
        List<Long> ids = List.of(1L, 2L);
        given(repository.findCategoriesByIdIn(ids)).willReturn(Collections.emptyList());
        //when
        //then
        assertThrows(CategoryNotFoundException.class, () -> service.getCategoriesById(ids));
    }
}
