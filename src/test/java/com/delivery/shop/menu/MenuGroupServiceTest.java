package com.delivery.shop.menu;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MenuGroupServiceTest {

    @InjectMocks
    MenuGroupService service;

    @Mock
    MenuGroupMapper menuGroupMapper;

    @Test
    public void registerMenuGroupTest() {
        MenuGroupDto menuGroup = new MenuGroupDto();
        menuGroup.setName("대표 메뉴");
        menuGroup.setContent("10000원");
        menuGroup.setShopId(1L);
        given(menuGroupMapper.saveMenuGroup(menuGroup))
                .willReturn(1);
        service.registerMenuGroup(menuGroup);
    }

    @Test
    public void menuGroupNameDuplicateTest() {
        given(menuGroupMapper.groupNameCheck("대표 메뉴"))
                .willReturn(1);
        given(menuGroupMapper.groupNameCheck("사이드 메뉴"))
                .willReturn(0);

        assertThat(service.groupNameCheck("대표 메뉴")).isEqualTo(true);
        assertThat(service.groupNameCheck("사이드 메뉴")).isEqualTo(false);
    }

    @Test
    public void menuGroupUpdateTest() {
        MenuGroupDto dto = new MenuGroupDto();
        dto.setName("대표 메뉴");
        dto.setContent("10000원");
        dto.setShopId(1L);

        given(menuGroupMapper.updateMenuGroup(dto))
                .willReturn(1);
        service.updateMenuGroup(dto);
    }
}
