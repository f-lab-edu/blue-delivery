package com.delivery.shop.menu;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "OPTIONS")
public class MenuOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_GROUP_ID")
    @JsonBackReference
    private MenuOptionGroup optionGroup;

    public MenuOption() {
    }

    public MenuOption(Long id, String name, int price, MenuOptionGroup optionGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.optionGroup = optionGroup;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MenuOption that = (MenuOption) obj;
        return getPrice() == that.getPrice()
                && Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }
}
