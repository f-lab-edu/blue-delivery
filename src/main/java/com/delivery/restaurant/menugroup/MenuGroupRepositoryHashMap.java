package com.delivery.restaurant.menugroup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

public class MenuGroupRepositoryHashMap implements MenuGroupRepository {

    private final Map<String, MenuGroup> menuGroupRepository;

    public MenuGroupRepositoryHashMap() {
        this.menuGroupRepository = new HashMap<>();
    }

    @Override
    public void saveMenuGroup(MenuGroup menuGroup) {
        if (menuGroupRepository.containsKey(menuGroup.getGroupName())) {
            throw new DuplicateKeyException("group already exists");
        }
        menuGroupRepository.put(menuGroup.getGroupName(), menuGroup);
    }

}
