package com.bluedelivery.api.menu;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.MenuOption;
import com.bluedelivery.domain.menu.MenuOptionGroup;

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
        optionGroup.setName(name);
        optionGroup.setOptionRequired(optionRequired);
        if (!optionRequired) {
            optionGroup.setMinimumOption(minimumOption);
        }
        optionGroup.setMaximumOption(maximumOption);
        for (MenuOption option : options) {
            optionGroup.addOption(option);
        }
        return optionGroup;
    }

    public boolean optionRequiredCheck(boolean optionRequired) {
        if (optionRequired & (this.getMinimumOption() == 0
                || this.getMaximumOption() < this.getMinimumOption())) {
            return true;
        }
        if (!optionRequired & this.getMaximumOption() == 0) {
            return true;
        }
        return false;
    }

}
