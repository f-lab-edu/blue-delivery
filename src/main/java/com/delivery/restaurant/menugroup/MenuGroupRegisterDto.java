package com.delivery.restaurant.menugroup;

import com.delivery.restaurant.menu.Menu;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MenuGroupRegisterDto {

    private Long id;

    @NotNull
    private String groupName;

    private String groupDescription;

    private List<Menu> menuList;

    public MenuGroupRegisterDto(Long id, String groupName, String groupDescription, List<Menu> menuList) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.menuList = menuList;
    }

    public MenuGroupRegisterDto() {
    }

    public MenuGroup toEntity() {
        return new MenuGroup(
                this.id,
                this.groupName,
                this.groupDescription,
                this.menuList
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

}
