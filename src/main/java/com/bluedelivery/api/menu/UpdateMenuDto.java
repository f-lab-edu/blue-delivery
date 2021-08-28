package com.bluedelivery.api.menu;

import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.Menu.MenuStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateMenuDto {

    private String name;

    private int price;

    private String composition;

    private String content;

    @NotNull
    private MenuStatus status;

    public UpdateMenuDto(String name, int price, String composition, String content, MenuStatus status) {
        this.name = name;
        this.price = price;
        this.composition = composition;
        this.content = content;
        this.status = status;
    }

}
