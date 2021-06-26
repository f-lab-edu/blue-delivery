package com.delivery.shop.menu;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface MenuMapper {

    int saveMenu(Menu menu);

    int menuNameCheck(String name);

    int menuStatusUpdate(Long id, MenuStatus status);

}
