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

    @Test
    @DisplayName("메뉴 생성 테스트")
    public void saveMenuTest() {
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setId(1L);
        dto.setMenuGroupId(1L);
        dto.setName("부리또");
        dto.setPrice(3500);

        //given
        when(service.getMenuById(1L)).thenReturn(dto.toEntity(dto));

        //when
        service.registerMenu(dto);

        //then
        assertThat(service.getMenuById(1L).getName()).isEqualTo("부리또");
        assertThat(service.getMenuById(1L).getPrice()).isEqualTo(3500);
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
        RegisterMenuDto dto = new RegisterMenuDto();
        dto.setId(1L);
        dto.setStatus(MenuStatus.DEFAULT);

        UpdateMenuDto updateDto = new UpdateMenuDto();
        dto.setId(1L);
        dto.setStatus(MenuStatus.SOLDOUT);

        //given
        when(service.getMenuById(1L)).thenReturn(dto.toEntity(dto));

        //when
        service.menuStatusUpdate(updateDto.getId(), updateDto);

        //then
        assertThat(service.getMenuById(1L).getStatus()).isEqualTo(MenuStatus.SOLDOUT);
    }

}
