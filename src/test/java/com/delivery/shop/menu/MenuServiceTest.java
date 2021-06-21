package com.delivery.shop.menu;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class MenuServiceTest {

    @InjectMocks
    MenuService service;

    @Mock
    MenuMapper menuMapper;

    @Test
    @DisplayName("메뉴 생성 테스트")
    public void saveMenuTest() {
        MenuDto dto = new MenuDto(1L, "부리또", 3500);

        given(menuMapper.saveMenu(dto)).willReturn(1);
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

}

