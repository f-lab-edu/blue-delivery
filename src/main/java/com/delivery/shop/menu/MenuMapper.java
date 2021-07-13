package com.delivery.shop.menu;

import static com.delivery.shop.menu.Menu.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuMapper {

    int saveMenu(Menu menu);

    int menuNameCheck(String name);

    int menuStatusUpdate(Long id, MenuStatus status);

    int setMainMenu(Long id);

    int countMainMenu();

    Menu findMenuById(Long id);

    List<Menu> findAllMenusByGroup();

    List<Menu> findMainMenus();

}
