package com.bluedelivery.api.category;

import javax.validation.constraints.Pattern;

import com.bluedelivery.application.category.CreateCategoryParam;
import com.bluedelivery.common.RegexConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
    @Pattern(regexp = RegexConstants.CATEGORY_NAME)
    private String name;
    
    public CreateCategoryParam toParam() {
        return new CreateCategoryParam(name);
    }
}
