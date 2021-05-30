package com.delivery.restaurant.menugroup;

import com.delivery.restaurant.menu.Menu;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MenuGroupRegisterDto {

    private Long id;

    @NotNull
    private String groupName;

    private String content;

    @NotNull
    private Long shopId;

    private List<Menu> menuList;

    public MenuGroupRegisterDto(Long id, String groupName, String content, Long shopId, List<Menu> menuList) {
        this.id = id;
        this.groupName = groupName;
        this.content = content;
        this.shopId = shopId;
        this.menuList = menuList;
    }

    public MenuGroupRegisterDto() {
    }

    public MenuGroup toEntity() {
        return new MenuGroup(
                this.id,
                this.groupName,
                this.content,
                this.shopId,
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

    public String getContent() {
        return content;
    }

    public void setContent(String groupDescription) {
        this.content = groupDescription;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
