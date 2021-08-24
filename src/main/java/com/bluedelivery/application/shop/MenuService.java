package com.bluedelivery.application.shop;

import com.bluedelivery.api.menu.RegisterMenuDto;
import com.bluedelivery.domain.menu.Menu;

public interface MenuService {

    void registerMenu(RegisterMenuDto dto);

    void setMainMenu(Long id);

    void updateMenuStatus(Long id, Menu.MenuStatus status);

    void deleteMenu(Long id);

    boolean duplicateMenuName(String name, Long id);

    boolean validateMainMenu(Long groupId);

    void mainMenuSizeOver();
}
