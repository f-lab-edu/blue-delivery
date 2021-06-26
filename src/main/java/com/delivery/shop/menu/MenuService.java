package com.delivery.shop.menu;

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
        Menu menu = dto.toEntity(dto);
        return menuMapper.saveMenu(menu);
    }

    public void menuStatusUpdate(Long id, UpdateMenuDto dto) {
        menuMapper.menuStatusUpdate(id, dto.getStatus());
    }

    public boolean menuNameCheck(String name) {
        return menuMapper.menuNameCheck(name) == 1;
    }
}
