package com.delivery.shop.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuGroupMapper {

    int saveMenuGroup(MenuGroupDto dto);

    int groupNameCheck(String name);

    List<MenuGroup> findMenuGroup(Long shopId);

    List<MenuGroup> findMenuByGroupId(Long groupId);

    int updateMenuGroup(MenuGroupDto dto);

    int deleteMenuGroup(Long id);

}
