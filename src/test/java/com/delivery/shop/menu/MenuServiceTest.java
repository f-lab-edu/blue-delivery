package com.delivery.shop.menu;

import static com.delivery.shop.menu.Menu.*;
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
class MenuServiceTest {

    @InjectMocks
    MenuService service;

    @Mock
    MenuMapper menuMapper;

    @Mock
    RegisterMenuDto saveDto;

    @Test
    @DisplayName("메뉴 저장 기능 테스트")
    public void saveMenuTest() {
        Menu menu = saveDto.toEntity();

        //given
        when(saveDto.toEntity()).thenReturn(menu);

        //when
        service.registerMenu(saveDto);

        //then
        verify(menuMapper, times(1)).saveMenu(menu);

    }


    @Test
    @DisplayName("메뉴 이름 중복 테스트")
    public void menuNameDuplicateTest() {

        //given
        given(menuMapper.menuNameCheck("부리또")).willReturn(1);
        given(menuMapper.menuNameCheck("퀘사디아")).willReturn(0);

        //then
        assertThat(service.menuNameCheck("부리또")).isEqualTo(true);
        assertThat(service.menuNameCheck("퀘사디아")).isEqualTo(false);


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
