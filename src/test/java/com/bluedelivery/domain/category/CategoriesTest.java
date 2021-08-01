package com.bluedelivery.domain.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopCategory;

class CategoriesTest {
    
    private Categories categories = new Categories();
    private Shop shop = new Shop();
    private ShopCategory chicken = new ShopCategory(shop, new Category("chicken"));
    private ShopCategory pizza = new ShopCategory(shop, new Category("pizza"));
    
    @Test
    @DisplayName("Categories는 불변객체이므로 원소를 추가/제거 하려고 하면 예외가 발생한다.")
    void getCategoriesReturnUnmodifiable() {
        //given
        Collection<ShopCategory> categories = this.categories.getCategories();
        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> categories.add(chicken));
        assertThrows(UnsupportedOperationException.class, () -> categories.remove(chicken));
    }
    
    @Test
    @DisplayName("Categories를 업데이트하면 새로 입력한 카테고리 목록만 존재한다.")
    void updateAll() {
        //given
        categories.updateAll(List.of(chicken, pizza));
        assertThat(categories.getCategories())
                .contains(chicken)
                .contains(pizza);
    
        //when
        categories.updateAll(List.of(new ShopCategory(shop, new Category("korean")), chicken));
    
        //then
        assertThat(categories.getCategories())
                .contains(new ShopCategory(shop, new Category("korean")))
                .contains(chicken)
                .doesNotContain(pizza);
    }
    
    @Test
    @DisplayName("중복되는 카테고리 2개를 추가하려고 하면 중복은 무시하고 1개만 추가한다.")
    void ignoreDuplicatedCategory() {
        //given
        
        //when
        categories.updateAll(List.of(chicken, new ShopCategory(shop, new Category("chicken"))));
        int count = categories.getCategories().size();
    
        //then
        assertThat(count).isEqualTo(1);
    }
}
