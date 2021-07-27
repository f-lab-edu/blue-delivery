package com.bluedelivery.application.shop.adapter;

import static com.bluedelivery.common.response.ErrorCode.*;
import static com.bluedelivery.domain.menu.Menu.*;

import org.springframework.stereotype.Service;

import com.bluedelivery.api.shop.RegisterMenuDto;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.infra.shop.MenuMapper;

@Service
public class MenuService {

    MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }


    public int registerMenu(RegisterMenuDto dto) {
        if (menuNameCheck(dto.getName())) {
            throw new ApiException(MENU_ALREADY_EXISTS);
        }
        Menu menu = dto.toEntity();
        return menuMapper.saveMenu(menu);
    }

    public int setMainMenu(Long id) {
        if (menuMapper.countMainMenu() > 5) {
            throw new ApiException(MAXIMUM_NUMBER_OF_MENU);
        }
        return menuMapper.setMainMenu(id);
    }

    public void menuStatusUpdate(Long id, MenuStatus status) {
        menuMapper.menuStatusUpdate(id, status);
    }

    public Menu getMenuById(Long id) {
        return menuMapper.findMenuById(id);
    }

    public boolean menuNameCheck(String name) {
        return menuMapper.menuNameCheck(name) == 1;
    }

}
