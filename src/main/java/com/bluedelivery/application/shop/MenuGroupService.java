package com.bluedelivery.application.shop;

import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.api.menu.UpdateMenuGroupDto;
import com.bluedelivery.domain.menu.MenuGroup;

public interface MenuGroupService {

    MenuGroup registerMenuGroup(RegisterMenuGroupDto dto);

    void updateMenuGroup(UpdateMenuGroupDto dto);
}
