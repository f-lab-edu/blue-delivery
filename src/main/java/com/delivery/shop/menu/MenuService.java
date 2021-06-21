package com.delivery.shop.menu;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public int registerMenu(MenuDto dto) {
        if (menuNameCheck(dto.getName())) {
            throw new DuplicateKeyException("menuName already exists");
        }
        return menuMapper.saveMenu(dto);
    }

    public boolean menuNameCheck(String name) {
        return menuMapper.menuNameCheck(name) == 1;
    }
}
