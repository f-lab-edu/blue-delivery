package com.delivery.shop.menu;

import static com.delivery.response.ErrorCode.*;
import static com.delivery.shop.menu.Menu.*;

import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;

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

    public int registerMainMenu(Long id) {
        if (menuMapper.countMainMenu() > 5) {
            throw new ApiException(MAXIMUM_NUMBER_OF_MENU);
        }
        return menuMapper.addMainMenu(id);
    }

    public int deleteMainMenu(Long id) {
        return menuMapper.deleteMainMenu(id);
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
