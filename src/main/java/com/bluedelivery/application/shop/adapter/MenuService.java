package com.bluedelivery.application.shop.adapter;

import static com.bluedelivery.common.response.ErrorCode.*;
import static com.bluedelivery.domain.menu.Menu.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.api.menu.RegisterMenuDto;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuGroup;
import com.bluedelivery.domain.menu.MenuGroupRepository;
import com.bluedelivery.domain.menu.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private final MenuRepository menuRepository;

    @Autowired
    private final MenuGroupRepository menuGroupRepository;

    public MenuService(MenuRepository menuRepository, MenuGroupRepository menuGroupRepository) {
        this.menuRepository = menuRepository;
        this.menuGroupRepository = menuGroupRepository;
    }

    public void registerMenu(RegisterMenuDto dto) {
        Menu menu = dto.toEntity();
        MenuGroup getMenuGroup = menuGroupRepository.findById(dto.getMenuGroupId())
                .orElseThrow(() -> new ApiException(MENU_GROUP_NOT_FOUND));

        if (duplicateMenuName(dto.getName())) {
            throw new ApiException(MENU_ALREADY_EXISTS);
        }
        menu.setMenuGroup(getMenuGroup);

        menuRepository.save(menu);
    }

    @Transactional
    public void setMainMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ApiException(MENU_NOT_FOUND));

        if (validateMainMenu(menu.getMenuGroup().getId())) {
            throw new ApiException(MAIN_MENU_NOT_VALIDATED);
        }

        if (menu.isMain() == false) {
            menu.setMain(true);
        }
        menu.setMain(false);
    }

    @Transactional
    public void updateMenuStatus(Long id, MenuStatus status) {
        Menu target = menuRepository.findById(id)
                .orElseThrow(() -> new ApiException(MENU_NOT_FOUND));

        target.setStatus(status);
    }

    public void deleteMenu(Long id) {
        Menu target = menuRepository.findById(id)
                .orElseThrow(() -> new ApiException(MENU_NOT_FOUND));

        menuRepository.delete(target);
    }

    public boolean duplicateMenuName(String name) {
        Menu target = menuRepository.findByName(name);
        if ( target != null) {
            return true;
        }
        return false;
    }

    public boolean validateMainMenu(Long groupId) {
        if (groupId != 1 || mainMenuSizeOver()) {
            return true;
        }
        return false;
    }

    public boolean mainMenuSizeOver() {
        Long target = menuRepository.findAll()
                .stream()
                .filter(m -> m.isMain() == true)
                .count();
        if (target >= 5) {
            throw new ApiException(MENU_MENU_SIZE_OVER);
        }
        return false;
    }

}
