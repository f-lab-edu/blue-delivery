package com.delivery.shop.menu;

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

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MenuOptionServiceTest {


    @Mock
    MenuOptionGroupRepository repository;

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

        verify(repository, times(1)).save(dto.toEntity());

    }

}
