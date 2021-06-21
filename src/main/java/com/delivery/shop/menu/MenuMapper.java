package com.delivery.shop.menu;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuMapper {

    int saveMenu(MenuDto dto);

    int menuNameCheck(String name);

}
