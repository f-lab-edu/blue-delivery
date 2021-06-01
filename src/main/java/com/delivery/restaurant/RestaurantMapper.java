package com.delivery.restaurant;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RestaurantMapper {
    
    @Select("SELECT * FROM RESTAURANTS WHERE ID = #{id}")
    Restaurant findRestaurantById(@Param("id") Long id);
    
    @Insert("INSERT INTO RESTAURANTS (NAME) VALUES( #{NAME} )")
    void save(@Param("name") String name);
    
    @Update("UPDATE RESTAURANTS SET NAME=#{name} WHERE ID = #{id}")
    void update(@Param("id") Long id, @Param("name") String name);
}
