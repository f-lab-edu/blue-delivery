package com.delivery.shop.menu;

import static com.delivery.shop.menu.Menu.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

public class RegisterMenuDto {

    private Long id;

    @NotNull
    private Long menuGroupId;

    @NotNull
    private String name;

    @NotNull
    private int price;

    private String composition; // 구성

    private String content; // 설명

    private MenuStatus status = MenuStatus.DEFAULT; // 메뉴 상태

    private List<MenuOptionGroup> menuOptionGroup;

    private LocalDateTime createdAt = LocalDateTime.now(); // 등록일

    private LocalDateTime modifiedAt; // 수정일

    public RegisterMenuDto() {
    }

    public RegisterMenuDto(Long id, Long menuGroupId, String name, int price,
                           String composition, String content, MenuStatus status,
                           List<MenuOptionGroup> menuOptionGroup, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.menuGroupId = menuGroupId;
        this.name = name;
        this.price = price;
        this.composition = composition;
        this.content = content;
        this.status = status;
        this.menuOptionGroup = menuOptionGroup;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    Menu toEntity() {
        return new Menu(
                this.getId(),
                this.getMenuGroupId(),
                this.getName(),
                this.getPrice(),
                this.getComposition(),
                this.getContent(),
                this.getStatus(),
                this.getMenuOptionGroup(),
                this.getCreatedAt(),
                this.getModifiedAt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(Long menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MenuStatus getStatus() {
        return status;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }

    public List<MenuOptionGroup> getMenuOptionGroup() {
        return menuOptionGroup;
    }

    public void setMenuOptionGroup(List<MenuOptionGroup> menuOptionGroup) {
        this.menuOptionGroup = menuOptionGroup;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
