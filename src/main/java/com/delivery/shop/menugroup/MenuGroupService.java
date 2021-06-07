package com.delivery.shop.menugroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MenuGroupService {

    private MenuGroupMapper menuGroupMapper;

    @Autowired
    public MenuGroupService(MenuGroupMapper menuGroupMapper) {
        this.menuGroupMapper = menuGroupMapper;
    }

    public void registerMenuGroup(MenuGroupDto dto) {
        if (this.groupNameCheck(dto.getName())) {
            throw new DuplicateKeyException("groupName already exists");
        } else {
            menuGroupMapper.saveMenuGroup(dto);
        }
    }

    public boolean groupNameCheck(String name) {
        return menuGroupMapper.groupNameCheck(name) == 1;
    }

}

