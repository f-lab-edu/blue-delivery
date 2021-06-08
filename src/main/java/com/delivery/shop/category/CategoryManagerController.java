package com.delivery.shop.category;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<CategoryListResponse> showCategoryList() {
        List<Category> categories = categoryManagerService.getAllCategories();
        return ResponseEntity.ok(new CategoryListResponse(categories));
    }
    
    @PostMapping
    public ResponseEntity addCategory(@RequestBody @Valid CategoryNameParam param) {
        categoryManagerService.addCategory(param.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PatchMapping("/{from}")
    public void renameCategory(@PathVariable("from") String from, @RequestBody @Valid CategoryNameParam to) {
        categoryManagerService.renameCategory(new RenameCategoryParam(from, to.getName()));
    }
    
    @DeleteMapping("/{name}")
    public void deleteCategory(@PathVariable("name") @RequestBody @Valid CategoryNameParam param) {
        categoryManagerService.deleteCategory(param.getName());
    }
    
}
