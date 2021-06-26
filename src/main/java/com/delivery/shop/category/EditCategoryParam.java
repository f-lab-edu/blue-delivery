package com.delivery.shop.category;

import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;

public class EditCategoryParam {
    private Long id;
    private String name;
    
    public EditCategoryParam(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    static class EditCategoryRequest {
        @Pattern(regexp = RegexConstants.CATEGORY_NAME)
        private String name;
    
        public EditCategoryRequest() {
        }
    
        public EditCategoryRequest(String name) {
            this.name = name;
        }
        
        public EditCategoryParam toParam(Long id) {
            return new EditCategoryParam(id, this.name);
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
    
}
