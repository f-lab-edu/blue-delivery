package com.bluedelivery.domain.menu;

import static com.bluedelivery.order.domain.ExceptionMessage.ORDERED_AND_MENU_ARE_DIFFERENT;

import java.io.Serializable;
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

import com.bluedelivery.order.domain.OrderItem;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "OPTIONS")
public class MenuOption implements Serializable {

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
                && Objects.equals(getName(), that.getName());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }
    
    public void validate(OrderItem.OrderItemOption orderOption) {
        if (!isEqualTo(orderOption)) {
            throw new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT);
        }
    }
    
    private boolean isEqualTo(OrderItem.OrderItemOption orderOption) {
        if (this.name.equals(orderOption.getName()) && this.price == orderOption.getPrice()) {
            return true;
        }
        return false;
    }
}
