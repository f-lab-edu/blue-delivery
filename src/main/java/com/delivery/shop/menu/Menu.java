package com.delivery.shop.menu;

import java.time.LocalDateTime;
import java.util.List;

public class  Menu {

    private Long id;
    private Long menuGroupId;
    private String name;
    private int price;
    private String composition;
    private String content;
    private MenuStatus status;
    private List<MenuOptionGroup> menuOptionGroup;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public enum MenuStatus {
        DEFAULT, SOLDOUT, HIDDEN;
    }

    public Menu() {
    }

    public Menu(Long id, Long menuGroupId, String name, int price, String composition, String content,
                MenuStatus status, List<MenuOptionGroup> menuOptionGroup, LocalDateTime createdAt,
                LocalDateTime modifiedAt) {
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

    public Long getId() {
        return id;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getComposition() {
        return composition;
    }

    public String getContent() {
        return content;
    }

    public MenuStatus getStatus() {
        return status;
    }

    public List<MenuOptionGroup> getMenuOptionGroup() {
        return menuOptionGroup;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

}
