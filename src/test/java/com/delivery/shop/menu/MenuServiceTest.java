package com.delivery.shop.menu;

import static com.delivery.shop.menu.Menu.*;
import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("메뉴 생성 테스트")
    public void saveMenuTest() {
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setId(1L);
        dto.setMenuGroupId(1L);
        dto.setName("부리또");
        dto.setPrice(3500);

        //given
        when(menuMapper.saveMenu(dto.toEntity(dto))).thenReturn(1);

        //when
        service.registerMenu(dto);

        //then
        verify(menuMapper).saveMenu(dto.toEntity(dto));
    }


    @Test
    @DisplayName("메뉴 이름 중복 테스트")
    public void menuNameDuplicateTest() {

        //given
        given(menuMapper.menuNameCheck("부리또")).willReturn(1);
        given(menuMapper.menuNameCheck("퀘사디아")).willReturn(0);

        //then
        Assertions.assertThat(service.menuNameCheck("부리또")).isEqualTo(true);
        Assertions.assertThat(service.menuNameCheck("퀘사디아")).isEqualTo(false);

    }

    @Test
    @DisplayName("메뉴 상태 변경 테스트")
    public void menuStatusUpdateTest() {
        UpdateMenuDto dto = new UpdateMenuDto();
        dto.setId(1L);
        dto.setStatus(MenuStatus.SOLDOUT);

        //given
        when(menuMapper.menuStatusUpdate(dto.getId(), dto.getStatus())).thenReturn(1);

        //when
        service.menuStatusUpdate(dto.getId(), dto);

        //then
        verify(menuMapper).menuStatusUpdate(dto.getId(), dto.getStatus());
    }

}
