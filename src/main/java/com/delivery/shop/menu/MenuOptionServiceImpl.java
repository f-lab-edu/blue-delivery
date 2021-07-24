package com.delivery.shop.menu;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;

@Service
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionGroupRepository optionGroupRepository;
    private final MenuOptionRepository optionRepository;

    public MenuOptionServiceImpl(MenuOptionGroupRepository optionGroupRepository,
                                 MenuOptionRepository optionRepository) {
        this.optionGroupRepository = optionGroupRepository;
        this.optionRepository = optionRepository;
    }

    public void registerMenuOptionGroup(MenuOptionGroupDto dto) {

        if (dto.optionRequiredCheck(dto.isOptionRequired())) {
            throw new ApiException(ErrorCode.OPTION_MIN_MAX_SELECT_ERROR);
        }
        optionGroupRepository.save(dto.toEntity());
    }

    @Override
    public void registerMenuOption(MenuOptionDto dto) {

        Optional<MenuOptionGroup> optionGroup = optionGroupRepository.findById(dto.getOptionGroupId());
        MenuOption option = dto.toEntity();

        option.setOptionGroup(optionGroup.orElseThrow(
                () -> new ApiException(ErrorCode.OPTION_GROUP_NOT_FOUND)));

        optionRepository.save(option);
    }

}
