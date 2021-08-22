package com.bluedelivery.api.menu;

import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.Menu.MenuStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class UpdateMenuDto {

    @NotNull
    private MenuStatus status;

    public UpdateMenuDto(MenuStatus status) {
        this.status = status;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }
}
