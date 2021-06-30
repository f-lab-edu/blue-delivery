package com.delivery.shop.menu;

import static com.delivery.shop.menu.Menu.*;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }


    public int registerMenu(RegisterMenuDto dto) {
        if (menuNameCheck(dto.getName())) {
            throw new DuplicateKeyException("menuName already exists");
        }
        Menu menu = dto.toEntity();
        return menuMapper.saveMenu(menu);
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
