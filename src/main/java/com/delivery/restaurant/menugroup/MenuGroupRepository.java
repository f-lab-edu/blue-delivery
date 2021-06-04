package com.delivery.restaurant.menugroup;

import org.springframework.stereotype.Repository;

@Repository
public interface MenuGroupRepository {

    void saveMenuGroup(MenuGroup menuGroup);

}
