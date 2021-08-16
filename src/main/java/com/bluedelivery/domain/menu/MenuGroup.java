package com.bluedelivery.domain.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MenuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_GROUP_ID")
    private Long id;

    @Column(name = "SHOP_ID")
    private Long shopId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT")
    private String content;

    @OneToMany(mappedBy = "menuGroup")
    private List<Menu> menuList = new ArrayList<>();

    public MenuGroup() {
    }

    public MenuGroup(Long id, Long shopId, String name, String content, List<Menu> menuList) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.content = content;
        this.menuList = menuList;
    }

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

    public List<Menu> getMenuList() {
        return menuList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MenuGroup menuGroup = (MenuGroup) obj;
        return Objects.equals(getShopId(), menuGroup.getShopId())
                && Objects.equals(getName(), menuGroup.getName())
                && Objects.equals(getContent(), menuGroup.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShopId(), getName(), getContent());
    }
}
