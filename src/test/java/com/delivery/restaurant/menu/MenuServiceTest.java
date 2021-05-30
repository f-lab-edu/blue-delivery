package com.delivery.restaurant.menu;

import com.delivery.restaurant.menugroup.MenuGroupRegisterDto;
import com.delivery.restaurant.menugroup.MenuGroupRepositoryHashMap;
import com.delivery.restaurant.menugroup.MenuGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {

    @Autowired
    MenuGroupService service = new MenuGroupService(new MenuGroupRepositoryHashMap());

    Menu menu = new Menu(0L, "타코");
    MenuGroupRegisterDto menuGroup = new MenuGroupRegisterDto(1L, "사이드", "5000원",2L ,new ArrayList<>(Arrays.asList(menu)));

    @BeforeEach
    public void registerMenuGroupTest() {
        assertDoesNotThrow(() -> service.registerMenuGroup(menuGroup));
    }

    @Test
    public void registerDuplicateMenuGroupNameTest() {
        assertThrows(DuplicateKeyException.class, () -> service.registerMenuGroup(menuGroup));
    }

}
