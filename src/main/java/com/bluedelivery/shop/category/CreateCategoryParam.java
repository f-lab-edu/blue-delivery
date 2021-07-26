package com.bluedelivery.shop.category;

import javax.validation.constraints.Pattern;

import com.bluedelivery.utility.RegexConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class CreateCategoryParam {
    private final String name;
    
    @Getter
    static class CreateCategoryRequest {
        @Pattern(regexp = RegexConstants.CATEGORY_NAME)
        private String name;
    
        public CreateCategoryRequest() {
        }
    
        public CreateCategoryRequest(String name) {
            this.name = name;
        }
    
        public CreateCategoryParam toParam() {
            return new CreateCategoryParam(name);
        }
        
        
    }
}
