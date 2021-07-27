package com.bluedelivery.domain.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bluedelivery.domain.category.Categories;
import com.bluedelivery.domain.category.Category;

class CategoriesTest {
    
    private Categories categories = new Categories();
    
    @Test
    @DisplayName("Categories는 불변객체이므로 원소를 추가/제거 하려고 하면 예외가 발생한다.")
    void getCategoriesReturnUnmodifiable() {
        //given
        Collection<Category> categories = this.categories.getCategories();
        //when
        //then
        assertThrows(UnsupportedOperationException.class, () -> categories.add(new Category()));
        assertThrows(UnsupportedOperationException.class, () -> categories.remove(new Category()));
    }
    
    @Test
    @DisplayName("Categories를 업데이트하면 새로 입력한 카테고리 목록만 존재한다.")
    void updateAll() {
        //given
        Category chicken = new Category("chicken");
        Category pizza = new Category("pizza");
        categories.updateAll(List.of(chicken, pizza));
        assertThat(categories.getCategories())
                .contains(chicken)
                .contains(pizza);
    
        //when
        Category korean = new Category("korean");
        categories.updateAll(List.of(korean, chicken));
    
        //then
        assertThat(categories.getCategories())
                .contains(korean)
                .contains(chicken)
                .doesNotContain(pizza);
    }
    
    @Test
    @DisplayName("중복되는 카테고리 2개를 추가하려고 하면 중복은 무시하고 1개만 추가한다.")
    void ignoreDuplicatedCategory() {
        //given
        Category chicken = new Category("chicken");
        Category chicken2 = new Category("chicken");
        
        //when
        categories.updateAll(List.of(chicken, chicken2));
        int count = categories.getCategories().size();
    
        //then
        assertThat(count).isEqualTo(1);
    }
}
