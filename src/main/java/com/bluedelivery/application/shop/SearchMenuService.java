package com.bluedelivery.application.shop;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluedelivery.shop.menu.Menu;
import com.bluedelivery.shop.menu.MenuMapper;

@Service
public class SearchMenuService {

    MenuMapper menuMapper;

    public SearchMenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    // Todo 가게 정보와 메뉴 정보를 한번에 가져오는 API를 위한 Service

    /**
     * 해당 shop 그룹별 전체 메뉴 조회
     * @return 메뉴 리스트
     */
    public List<Menu> getAllMenus() {
        return menuMapper.findAllMenusByGroup();
    }

    /**
     * 해당 shop 대표 메뉴 전체 조회
     * @return  대표 메뉴 리스트
     */
    public List<Menu> getMainMenus() {
        return menuMapper.findMainMenus();
    }
}
