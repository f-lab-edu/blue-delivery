package com.bluedelivery.api.menu;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateMenuGroupDto {

    @NotNull
    private Long id;

    @NotNull
    private Long shopId;

    @NotNull @NotBlank
    private String name;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
