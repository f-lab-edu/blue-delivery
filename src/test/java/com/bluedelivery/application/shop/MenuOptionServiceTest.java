package com.bluedelivery.application.shop;

import static java.util.Optional.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.bluedelivery.api.menu.MenuOptionDto;
import com.bluedelivery.api.menu.MenuOptionGroupDto;
import com.bluedelivery.application.shop.adapter.MenuOptionServiceImpl;
import com.bluedelivery.domain.menu.MenuOption;
import com.bluedelivery.domain.menu.MenuOptionGroup;
import com.bluedelivery.domain.menu.MenuOptionGroupRepository;
import com.bluedelivery.domain.menu.MenuOptionRepository;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MenuOptionServiceTest {


    @Mock
    MenuOptionGroupRepository optionGroupRepository;

    @Mock
    MenuOptionRepository menuOptionRepository;

    @InjectMocks
    MenuOptionServiceImpl service;

    @Test
    @DisplayName("메뉴 옵션 그룹 생성 테스트")
    public void menuOptionGroupRegisterTest() {
        List<MenuOption> menuOptions = new ArrayList<>();

        MenuOption menuOption = new MenuOption();
        menuOption.setId(1L);
        menuOption.setName("매운맛");
        menuOption.setPrice(0);

        menuOptions.add(menuOption);

        MenuOptionGroupDto dto = new MenuOptionGroupDto();
        dto.setMenuId(1L);
        dto.setName("맛선택");
        dto.setOptionRequired(true);
        dto.setMinimumOption(1);
        dto.setMaximumOption(3);
        dto.setOptions(menuOptions);

        service.registerMenuOptionGroup(dto);

        verify(optionGroupRepository, times(1)).save(dto.toEntity());

    }

    @Test
    @DisplayName("메뉴 옵션 생성 테스트")
    public void registerMenuOptionTest() {

        MenuOptionDto dto = new MenuOptionDto();
        dto.setOptionGroupId(1L);
        dto.setName("매운맛");
        dto.setPrice(0);

        MenuOptionGroup menuOptionGroup = new MenuOptionGroup();
        menuOptionGroup.setId(1L);

        when(optionGroupRepository.findById(dto.getOptionGroupId())).thenReturn(of(menuOptionGroup));

        service.registerMenuOption(dto);

        verify(menuOptionRepository, times(1)).save(dto.toEntity());

    }

}
