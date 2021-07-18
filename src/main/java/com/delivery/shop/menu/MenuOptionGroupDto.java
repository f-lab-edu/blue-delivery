package com.delivery.shop.menu;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuOptionGroupDto {

    @NotNull @NotBlank
    private String name;

    private Long menuId;

    @NotNull
    private boolean optionRequired;

    private int minimumOption;

    @NotNull
    private int maximumOption;

    private List<MenuOption> options = new ArrayList<>();

    public MenuOptionGroupDto() {
    }

    public MenuOptionGroup toEntity() {
        MenuOptionGroup optionGroup = new MenuOptionGroup();
        optionGroup.setMenuId(menuId);
        optionGroup.setName(name);
        optionGroup.setOptionRequired(optionRequired);
        optionGroup.setMinimumOption(minimumOption);
        optionGroup.setMaximumOption(maximumOption);
        for (MenuOption option : options) {
            optionGroup.addOption(option);
        }
        return optionGroup;
    }

}
