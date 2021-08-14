package com.bluedelivery.application.shop.adapter;

import static com.bluedelivery.common.response.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.api.menu.UpdateMenuGroupDto;
import com.bluedelivery.application.shop.MenuGroupService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.menu.MenuGroup;
import com.bluedelivery.domain.menu.MenuGroupRepository;


@Service
public class MenuGroupServiceImpl implements MenuGroupService {

    private final MenuGroupRepository repository;

    public MenuGroupServiceImpl(MenuGroupRepository repository) {
        this.repository = repository;
    }

    public MenuGroup registerMenuGroup(RegisterMenuGroupDto dto) {
        if (duplicateGroupName(dto.getName())) {
            throw new ApiException(GROUP_ALREADY_EXISTS);
        }
        return repository.save(dto.toEntity());
    }

    public void updateMenuGroup(UpdateMenuGroupDto dto) {
        MenuGroup target = repository.findById(dto.getId()).orElseThrow(() -> new ApiException(MENU_GROUP_NOT_FOUND));

        target.setName(dto.getName());
        target.setContent(dto.getContent());
        repository.save(target);
    }

    public void deleteMenuGroup(Long id) {
        MenuGroup target = repository.findById(id).orElseThrow(() -> new ApiException(MENU_GROUP_NOT_FOUND));
        repository.delete(target);
    }

    public boolean duplicateGroupName(String name) {
        MenuGroup findName = repository.findByName(name);
        if (findName != null) {
            return true;
        }
        return false;
    }

}
