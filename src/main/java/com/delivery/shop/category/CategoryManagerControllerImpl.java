package com.delivery.shop.category;

import static com.delivery.shop.category.CreateCategoryParam.*;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.shop.category.EditCategoryParam.EditCategoryRequest;

@RestController
public class CategoryManagerControllerImpl implements CategoryManagerController {
    
    private final CategoryManagerService categoryManagerService;
    
    public CategoryManagerControllerImpl(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
    
    public ResponseEntity<GetAllCategoriesResponse> getAllCategories() {
        GetAllCategoriesResponse body = new GetAllCategoriesResponse(
                categoryManagerService.getAllCategories().stream()
                        .map(Category::toResponse)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest dto) {
        categoryManagerService.addCategory(dto.toParam());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryManagerService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    public ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody EditCategoryRequest request) {
        categoryManagerService.editCategory(request.toParam(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
