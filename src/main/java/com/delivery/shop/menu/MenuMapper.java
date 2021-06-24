package com.delivery.shop.menu;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.delivery.shop.menu.Menu.Status;

@Mapper
@Repository
public interface MenuMapper {

    int saveMenu(Menu menu);

    int menuNameCheck(String name);

    int menuStatusUpdate(Long id, Status status);
}
