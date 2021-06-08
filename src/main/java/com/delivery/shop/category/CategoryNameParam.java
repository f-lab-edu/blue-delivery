package com.delivery.shop.category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.delivery.utility.RegexConstants;

public class CategoryNameParam {
    
    @NotNull
    @Pattern(regexp = RegexConstants.CATEGORY_NAME)
    @Size(min = 1, max = 10)
    private String name;
    
    public CategoryNameParam() {
    }
    
    public CategoryNameParam(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
