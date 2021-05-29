package com.delivery.restaurant.menugroup;

import org.springframework.dao.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;

public class MenuGroupRepositoryHashMap implements MenuGroupRepository {

    private final Map<String, MenuGroup> menuGroupRepository;

    public MenuGroupRepositoryHashMap() {
        this.menuGroupRepository = new HashMap<>();
    }

    @Override
    public void saveMenuGroup(MenuGroup menuGroup) {
        if(menuGroupRepository.containsKey(menuGroup.getGroupName())) {
            throw new DuplicateKeyException("group already exists");
        }
        menuGroupRepository.put(menuGroup.getGroupName(), menuGroup);
    }

}
