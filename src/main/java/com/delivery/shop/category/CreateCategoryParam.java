package com.delivery.shop.category;

import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;

public class CreateCategoryParam {
    private String name;
    
    public CreateCategoryParam(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    static class CreateCategoryRequest {
        @Pattern(regexp = RegexConstants.CATEGORY_NAME)
        private String name;
    
        public CreateCategoryRequest() {
        }
    
        public CreateCategoryParam toParam() {
            return new CreateCategoryParam(name);
        }
    
        public String getName() {
            return name;
        }
    
    }
}
