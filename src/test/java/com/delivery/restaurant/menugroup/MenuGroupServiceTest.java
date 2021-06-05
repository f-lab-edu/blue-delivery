package com.delivery.restaurant.menugroup;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


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
        menuGroup.setRestaurantId(1L);
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
}
