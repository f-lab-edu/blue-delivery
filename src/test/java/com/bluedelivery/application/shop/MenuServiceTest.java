package com.bluedelivery.application.shop;

import static com.bluedelivery.common.response.ErrorCode.*;
import static com.bluedelivery.domain.menu.Menu.MenuStatus.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.bluedelivery.api.menu.RegisterMenuDto;
import com.bluedelivery.application.shop.adapter.MenuService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuGroup;
import com.bluedelivery.domain.menu.MenuGroupRepository;
import com.bluedelivery.domain.menu.MenuRepository;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MenuServiceTest {

    @InjectMocks
    MenuService service;

    @Mock
    MenuRepository menuRepository;

    @Mock
    MenuGroupRepository menuGroupRepository;


    @Test
    @DisplayName("메뉴 저장 기능 테스트")
    public void saveMenuTest() {
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setMenuGroupId(1L);
        dto.setName("부리또");
        dto.setPrice(3500);
        dto.setComposition("1인분");
        dto.setContent("부리또 + 피클");
        dto.setStatus(DEFAULT);

        MenuGroup getMenuGroup = new MenuGroup();
        getMenuGroup.setId(1L);

        given(menuGroupRepository.findById(dto.getMenuGroupId()))
                .willReturn(Optional.of(getMenuGroup));

        service.registerMenu(dto);

        verify(menuRepository, times(1)).save(dto.toEntity());
    }

    @Test
    @DisplayName("메뉴 추가시 이름이 중복일때 예외발생 테스트")
    public void saveMenuFailTest() {
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setName("부리또");
        dto.setPrice(3500);

        given(menuRepository.save(dto.toEntity()))
                .willThrow(new ApiException(MENU_ALREADY_EXISTS));

        assertThrows(ApiException.class,
                () -> menuRepository.save(dto.toEntity())).getError();
    }

    @Test
    @DisplayName("대표메뉴 수 6개 초과 추가시 예외발생")
    public void mainMenuSizeOverTest() {

        given(menuRepository.findAll()
                .stream()
                .filter(m -> (m.getMenuGroup().getId() == 1))
                .count() == 6)
                .willThrow(new ApiException(MENU_MENU_SIZE_OVER));

        assertThrows(ApiException.class,
                () -> menuRepository.findAll()).getError();
    }


    @Test
    @DisplayName("메뉴 상태 변경 테스트")
    public void menuStatusUpdateTest() {
        Menu menu = new Menu();
        menu.setStatus(DEFAULT);

        given(menuRepository.findById(1L)).willReturn(Optional.of(menu));

        service.updateMenuStatus(1L, HIDDEN);

        assertThat(menu.getStatus()).isEqualTo(HIDDEN);
    }

    @Test
    @DisplayName("메뉴 삭제 테스트")
    public void deleteMenuTest() {
        Menu menu = new Menu();

        given(menuRepository.findById(1L)).willReturn(Optional.of(menu));

        service.deleteMenu(1L);

        verify(menuRepository, times(1)).delete(menu);
    }

}
