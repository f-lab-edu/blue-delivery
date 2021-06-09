package com.delivery.shop.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.shop.shop.SearchedShopData;

@RestController
@RequestMapping("/categories")
public class CategoryManagerController {
    
    private final CategoryManagerService categoryManagerService;
    
    public CategoryManagerController(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
    
    @GetMapping
    public List<CategoryData> getAllCategories() {
        return categoryManagerService.getAllCategories();
    }
    
    @PutMapping
    public void updateCategories() {
        categoryManagerService.updateCategories();
    }
    
    @GetMapping("/{id}/shops")
    public List<SearchedShopData> getShopsByCategory(
            @PathVariable("id") Long id, @Param("offset") Integer offset) {
        return categoryManagerService.getShopsByCategory(id, offset);
    }
    
}
