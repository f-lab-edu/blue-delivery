package com.delivery.shop.menu;


import org.springframework.stereotype.Service;

@Service
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionGroupRepository repository;

    public MenuOptionServiceImpl(MenuOptionGroupRepository repository) {
        this.repository = repository;
    }

    public void registerMenuOptionGroup(MenuOptionGroupDto dto) {

        MenuOptionGroup optionGroup = dto.toEntity();
        repository.save(optionGroup);
    }

}
