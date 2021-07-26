package com.bluedelivery.shop.category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    @DisplayName("같은 이름을 갖는 카테고리는 동등한 카테고리다.")
    void categoryEqualityTest() {
        //given
        Category pizza1 = new Category("pizza");
        Category pizza2 = new Category("pizza");
        
        //when
        //then
        assertThat(pizza1).isEqualTo(pizza2);
    }
}
