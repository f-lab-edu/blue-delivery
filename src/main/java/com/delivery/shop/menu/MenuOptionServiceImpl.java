package com.delivery.shop.menu;

import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;

@Service
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionGroupRepository repository;

    public MenuOptionServiceImpl(MenuOptionGroupRepository repository) {
        this.repository = repository;
    }

    public void registerMenuOptionGroup(MenuOptionGroupDto dto) {

        if (dto.optionRequiredCheck(dto.isOptionRequired())) {
            throw new ApiException(ErrorCode.OPTION_MIN_MAX_SELECT_ERROR);
        }
        repository.save(dto.toEntity());
    }

}
