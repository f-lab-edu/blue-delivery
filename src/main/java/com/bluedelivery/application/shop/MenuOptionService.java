package com.bluedelivery.application.shop;


import com.bluedelivery.api.menu.MenuOptionDto;
import com.bluedelivery.api.menu.MenuOptionGroupDto;

public interface MenuOptionService {

    void registerMenuOptionGroup(MenuOptionGroupDto dto);

    void registerMenuOption(MenuOptionDto dto);
}
