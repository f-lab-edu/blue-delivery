package com.bluedelivery.api.menu;

import static com.bluedelivery.domain.menu.Menu.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bluedelivery.domain.menu.Menu;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterMenuDto {

    private Long menuGroupId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private int price;

    private String composition;

    private String content;

    private MenuStatus status = MenuStatus.DEFAULT;

    private boolean isMain = false;

    public Menu toEntity() {
        return Menu.builder()
                .name(getName())
                .price(getPrice())
                .composition(getComposition())
                .content(getContent())
                .status(getStatus())
                .isMain(isMain())
                .build();
    }

    public void setMenuGroupId(Long menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }

}
