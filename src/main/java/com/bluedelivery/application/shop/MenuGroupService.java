package com.bluedelivery.application.shop;

import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.domain.menu.MenuGroup;

public interface MenuGroupService {

    MenuGroup registerMenuGroup(RegisterMenuGroupDto dto);

}
