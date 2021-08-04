package com.bluedelivery.domain.shop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bluedelivery.domain.category.Category;

class ShopTest {
    
    @Test
    @DisplayName("가게의 카테고리 목록을 갱신한다. 2개의 중복되지 않는 카테고리를 파라미터로 주면 카테고리의 개수가 2개가 된다.")
    void updateCategoriesTest() {
        //given
        Shop shop = new Shop();
        Category pizza = new Category(1L, "pizza");
        Category chicken = new Category(2L, "chicken");
        
        //when
        shop.updateCategoryIds(List.of(pizza, chicken));
        int count = shop.getCategoryIds().size();
        
        //then
        assertThat(count).isEqualTo(2);
    }
    
}
