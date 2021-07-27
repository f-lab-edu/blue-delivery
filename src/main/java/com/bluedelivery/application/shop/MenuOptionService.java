package com.bluedelivery.application.shop;


import com.bluedelivery.api.shop.MenuOptionDto;
import com.bluedelivery.api.shop.MenuOptionGroupDto;

public interface MenuOptionService {

    void registerMenuOptionGroup(MenuOptionGroupDto dto);

    void registerMenuOption(MenuOptionDto dto);
}
