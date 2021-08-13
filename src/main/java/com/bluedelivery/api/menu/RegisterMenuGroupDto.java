package com.bluedelivery.api.menu;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.MenuGroup;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegisterMenuGroupDto {

    @NotNull
    private Long shopId;

    @NotNull @NotBlank
    private String name;

    private String content;

    public MenuGroup toEntity() {
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setShopId(this.shopId);
        menuGroup.setGroupName(this.name);
        menuGroup.setContent(this.content);
        return menuGroup;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String groupName) {
        this.name = groupName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
