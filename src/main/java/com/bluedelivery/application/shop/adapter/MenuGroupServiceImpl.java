package com.bluedelivery.application.shop.adapter;

import static com.bluedelivery.common.response.ErrorCode.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.application.shop.MenuGroupService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.menu.MenuGroup;
import com.bluedelivery.domain.menu.MenuGroupRepository;
import com.bluedelivery.infra.shop.MenuGroupMapper;


@Service
public class MenuGroupServiceImpl implements MenuGroupService {

    @Autowired
    private MenuGroupMapper menuGroupMapper;

    @Autowired
    private final MenuGroupRepository repository;

    public MenuGroupServiceImpl(MenuGroupMapper menuGroupMapper, MenuGroupRepository repository) {
        this.repository = repository;
    }

    public MenuGroup registerMenuGroup(RegisterMenuGroupDto dto) {
        if (duplicateGroupName(dto.getName())) {
            throw new ApiException(GROUP_ALREADY_EXISTS);
        }
        return repository.save(dto.toEntity());
    }

    public List<MenuGroup> getMenuGroup(Long id)  {
        return menuGroupMapper.findMenuGroup(id);
    }

    public int updateMenuGroup(RegisterMenuGroupDto dto) {
        return menuGroupMapper.updateMenuGroup(dto);
    }

    public int deleteMenuGroup(Long id) {
        return menuGroupMapper.deleteMenuGroup(id);
    }

    public boolean duplicateGroupName(String name) {
        MenuGroup findName = repository.findByName(name);
        if (findName != null) {
            return true;
        }
        return false;
    }

}

