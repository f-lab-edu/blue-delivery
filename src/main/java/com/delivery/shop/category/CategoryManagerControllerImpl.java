package com.delivery.shop.category;

import static com.delivery.response.HttpResponse.response;
import static com.delivery.shop.category.CreateCategoryParam.*;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.response.HttpResponse;
import com.delivery.shop.category.EditCategoryParam.EditCategoryRequest;

@RestController
public class CategoryManagerControllerImpl implements CategoryManagerController {
    
    private final CategoryManagerService categoryManagerService;
    
    public CategoryManagerControllerImpl(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
    
    public ResponseEntity<HttpResponse<List<Categories>>> getAllCategories() {
        List<Category> categories = categoryManagerService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response(categories));
    }
    
    public ResponseEntity<HttpResponse<Category>> createCategory(CreateCategoryRequest dto) {
        try {
            Category category = categoryManagerService.addCategory(dto.toParam());
            return ResponseEntity.status(HttpStatus.CREATED).body(response(category));
        } catch (DuplicateKeyException ex) {
            throw new ApiException(ErrorCode.DUPLICATE_CATEGORY);
        }
    }
    
    public ResponseEntity<?> deleteCategory(Long id) {
        long removed = categoryManagerService.deleteCategory(id);
        if (removed == 0) {
            throw new  ApiException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response("successfully removed"));
    }
    
    public ResponseEntity<?> editCategory(Long id, EditCategoryRequest request) {
        try {
            categoryManagerService.editCategory(request.toParam(id));
        } catch (CategoryNotFoundException ex) {
            throw new ApiException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response("update successfully done"));
    }
    
}
