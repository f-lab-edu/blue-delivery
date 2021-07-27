package com.bluedelivery.shop.category;

import javax.validation.constraints.Pattern;

import com.bluedelivery.common.RegexConstants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EditCategoryParam {
    private final Long id;
    private final String name;
    
    @Getter
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
    }
    
}
