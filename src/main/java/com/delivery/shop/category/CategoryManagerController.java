package com.delivery.shop.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryManagerController {
    
    private final CategoryManagerService categoryManagerService;
    
    public CategoryManagerController(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
    
    @GetMapping
    public ResponseEntity<CategoryResponses> showCategoryList() {
        CategoryResponses allCategories = categoryManagerService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
    
    @PostMapping("/update")
    public ResponseEntity updateCategory() {
        categoryManagerService.updateCategory();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
