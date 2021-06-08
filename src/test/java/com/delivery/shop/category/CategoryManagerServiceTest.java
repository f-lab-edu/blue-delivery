package com.delivery.shop.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryManagerServiceTest {
    
    @Mock
    CategoryRepository categoryRepository;
    
    @InjectMocks
    CategoryManagerService categoryManagerService;
    
    @Test
    void categoryNotFoundTest() {
        String name = "name";
        RenameCategoryParam param = new RenameCategoryParam("name1", "name2");
        when(categoryRepository.deleteByName(name)).thenReturn(0);
        when(categoryRepository.updateName(param)).thenReturn(0);
        
        assertThrows(IllegalArgumentException.class,
                () -> categoryManagerService.deleteCategory(name)
        );
        assertThrows(IllegalArgumentException.class,
                () -> categoryManagerService.renameCategory(param)
        );
    }
    
    
}
