package com.delivery.shop.menu;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
class MenuServiceTest {

    @InjectMocks
    MenuService service;

    @Mock
    MenuMapper menuMapper;

    @Test
    @DisplayName("메뉴 생성 테스트")
    public void saveMenuTest() {
        RegisterMenuDto dto = new RegisterMenuDto(1L,
                1L,
                "부리또",
                3500,
                "1인분",
                "부리또+피클");
        Menu menu = dto.toEntity(dto);
        given(menuMapper.saveMenu(menu)).willReturn(1);
        service.registerMenu(dto);
    }


    @Test
    @DisplayName("메뉴 이름 중복 테스트")
    public void menuNameDuplicateTest() {
        given(menuMapper.menuNameCheck("부리또")).willReturn(1);
        given(menuMapper.menuNameCheck("퀘사디아")).willReturn(0);

        Assertions.assertThat(service.menuNameCheck("부리또")).isEqualTo(true);
        Assertions.assertThat(service.menuNameCheck("퀘사디아")).isEqualTo(false);

    }

    @Test
    @DisplayName("메뉴 상태 변경 테스트")
    public void menuStatusUpdateTest() {
        UpdateMenuDto dto = new UpdateMenuDto();
        Long id = 1L;
        dto.setStatus(MenuStatus.SOLDOUT);
        given(menuMapper.menuStatusUpdate(id, dto.getStatus())).willReturn(1);

        service.menuStatusUpdate(id, dto);
    }

}
