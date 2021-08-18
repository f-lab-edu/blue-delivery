package com.bluedelivery.infra.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.domain.menu.MenuGroup;


@Mapper
@Repository
public interface MenuGroupMapper {

    List<MenuGroup> findMenuGroup(Long shopId);

    List<MenuGroup> findMenuByGroupId(Long groupId);

    int updateMenuGroup(RegisterMenuGroupDto dto);

    int deleteMenuGroup(Long id);

}
