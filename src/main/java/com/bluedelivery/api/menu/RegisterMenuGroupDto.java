package com.bluedelivery.api.menu;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.MenuGroup;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
        menuGroup.setName(this.name);
        menuGroup.setContent(this.content);
        return menuGroup;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setName(String groupName) {
        this.name = groupName;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
