package com.bluedelivery.infra.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bluedelivery.api.shop.MenuGroupDto;
import com.bluedelivery.domain.menu.MenuGroup;


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
