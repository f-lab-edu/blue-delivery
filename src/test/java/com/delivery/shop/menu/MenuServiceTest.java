package com.delivery.shop.menu;

import static com.delivery.response.ErrorCode.*;
import static com.delivery.shop.menu.Menu.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class MenuServiceTest {

    @InjectMocks
    MenuService service;

    @Mock
    MenuMapper menuMapper;


    @Test
    @DisplayName("메뉴 저장 기능 테스트")
    public void saveMenuTest() {
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setMenuGroupId(1L);
        dto.setName("부리또");
        dto.setPrice(3500);
        dto.setComposition("1인분");
        dto.setContent("부리또 + 피클");
        dto.setStatus(MenuStatus.DEFAULT);

        //when
        service.registerMenu(dto);

        //then
        verify(menuMapper, times(1)).saveMenu(dto.toEntity());

    }

    @Test
    @DisplayName("대표메뉴 추가 기능 테스트")
    public void addMainMenu() {

        //when
        service.registerMainMenu(1L);

        //then
        verify(menuMapper, times(1)).addMainMenu(1L);
    }

    @Test
    @DisplayName("대표메뉴 수 6개 초과 추가시 예외발생")
    public void addMainMenuMaximumTest() {
        Menu menu = Menu.builder()
                .id(7L)
                .build();

        when(service.registerMainMenu(menu.getId()))
                .thenThrow(new ApiException(MAXIMUM_NUMBER_OF_MENU));

        ErrorCode errorCode =
                assertThrows(ApiException.class,
                        () -> service.registerMainMenu(menu.getId())).getError();

        assertThat(errorCode).isEqualTo(MAXIMUM_NUMBER_OF_MENU);

    }


    @Test
    @DisplayName("대표메뉴 삭제 기능 테스트")
    public void deleteMainMenu() {

        //when
        service.deleteMainMenu(1L);

        //then
        verify(menuMapper, times(1)).deleteMainMenu(1L);
    }


    @Test
    @DisplayName("메뉴 이름 중복 테스트")
    public void menuNameDuplicateTest() {

        //given
        given(menuMapper.menuNameCheck("부리또")).willReturn(1);
        given(menuMapper.menuNameCheck("퀘사디아")).willReturn(0);

        //then
        assertThat(service.menuNameCheck("부리또")).isTrue();
        assertThat(service.menuNameCheck("퀘사디아")).isFalse();


    }

    @Test
    @DisplayName("메뉴 상태 변경 테스트")
    public void menuStatusUpdateTest() {

        //when
        service.menuStatusUpdate(1L, MenuStatus.SOLDOUT);

        //then
        verify(menuMapper, times(1)).menuStatusUpdate(1L, MenuStatus.SOLDOUT);

    }

}
