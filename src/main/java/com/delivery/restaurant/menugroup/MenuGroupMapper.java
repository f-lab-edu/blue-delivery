package com.delivery.restaurant.menugroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuGroupMapper {

    int saveMenuGroup(MenuGroupDto dto) throws NotFoundException;

    int groupNameCheck(String name);

    int shopIdCheck(int id);

    List<MenuGroupDto> getMenuGroup(Long id);

}
