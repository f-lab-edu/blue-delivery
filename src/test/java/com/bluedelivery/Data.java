package com.bluedelivery;

import com.bluedelivery.domain.menu.Menu;

public class Data {
    public static Menu.MenuBuilder chicken() {
        return Menu.builder()
                .id(1L)
                .name("양념치킨")
                .composition("양념치킨과 치킨무 세트")
                .content("양념이지만 바삭바삭한 양념치킨입니다.")
                .isMain(true)
                .menuGroupId(1L)
                .price(15000)
                .status(Menu.MenuStatus.DEFAULT);
    }
    
}
