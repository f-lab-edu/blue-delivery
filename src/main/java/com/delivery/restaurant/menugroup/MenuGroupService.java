package com.delivery.restaurant.menugroup;

import org.springframework.stereotype.Service;

@Service
public class MenuGroupService {

    private MenuGroupRepository repository;

    public MenuGroupService(MenuGroupRepository repository) {
        this.repository = repository;
    }

    public void registerMenuGroup(MenuGroupRegisterDto dto) {
        repository.saveMenuGroup(dto.toEntity());
    }

}
