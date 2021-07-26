package com.bluedelivery.shop.menu;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MenuGroupServiceTest {

    @InjectMocks
    MenuGroupService service;

    @Mock
    MenuGroupMapper menuGroupMapper;

    @Test
    @DisplayName("메뉴 그룹 추 테스트")
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
    @DisplayName("메뉴 그룹 이름 중복 테스트")
    public void menuGroupNameDuplicateTest() {
        given(menuGroupMapper.groupNameCheck("대표 메뉴"))
                .willReturn(1);
        given(menuGroupMapper.groupNameCheck("사이드 메뉴"))
                .willReturn(0);

        assertThat(service.groupNameCheck("대표 메뉴")).isEqualTo(true);
        assertThat(service.groupNameCheck("사이드 메뉴")).isEqualTo(false);
    }

    @Test
    @DisplayName("메뉴 그룹 수정 테스트")
    public void updateMenuGroup() {
        MenuGroupDto dto = new MenuGroupDto();
        dto.setName("대표 메뉴");
        dto.setContent("10000원");
        dto.setShopId(1L);

        given(menuGroupMapper.updateMenuGroup(dto))
                .willReturn(1);
        service.updateMenuGroup(dto);
    }

    @Test
    @DisplayName("메뉴 그룹 삭제 테스트")
    public void deleteMenuGroupTest() {
        MenuGroupDto dto = new MenuGroupDto();
        dto.setId(1L);
        dto.setShopId(1L);
        dto.setName("사이드 메뉴");
        dto.setContent("5000원");

        given(menuGroupMapper.deleteMenuGroup(dto.getId()))
                .willReturn(1);
        service.deleteMenuGroup(dto.getId());
    }

    @Test
    @DisplayName("메뉴 그룹 삭제 실패")
    public void deleteFileMenuGroupTest() {
        given(menuGroupMapper.deleteMenuGroup(2L))
                .willReturn(0);
        service.deleteMenuGroup(2L);
    }

}
