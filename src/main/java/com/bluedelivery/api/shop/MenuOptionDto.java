package com.bluedelivery.api.shop;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.MenuOption;

public class MenuOptionDto {

    @NotNull @NotBlank
    private String name;

    private Long optionGroupId;

    @NotNull
    private int price;

    public MenuOptionDto() {
    }

    public MenuOptionDto(String name, Long optionGroupId, int price) {
        this.name = name;
        this.optionGroupId = optionGroupId;
        this.price = price;
    }

    public MenuOption toEntity() {
        MenuOption menuOption = new MenuOption();
        menuOption.setName(this.name);
        menuOption.setPrice(this.price);
        return menuOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOptionGroupId() {
        return optionGroupId;
    }

    public void setOptionGroupId(Long optionGroupId) {
        this.optionGroupId = optionGroupId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
