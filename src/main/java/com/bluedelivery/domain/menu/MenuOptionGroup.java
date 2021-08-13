package com.bluedelivery.domain.menu;

import static com.bluedelivery.order.domain.ExceptionMessage.ORDERED_AND_MENU_ARE_DIFFERENT;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluedelivery.order.domain.OrderItem;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "OPTION_GROUP")
public class MenuOptionGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "OPTION_REQUIRED")
    private boolean optionRequired;

    @Column(name = "MINIMUM_OPTION")
    private int minimumOption;

    @Column(name = "MAXIMUM_OPTION")
    private int maximumOption;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<MenuOption> options = new ArrayList<>();

    public MenuOptionGroup() {
    }

    public MenuOptionGroup(Long id, String name, Long menuId, boolean optionRequired, int minimumOption,
                           int maximumOption, List<MenuOption> options) {
        this.id = id;
        this.name = name;
        this.menuId = menuId;
        this.optionRequired = optionRequired;
        this.minimumOption = minimumOption;
        this.maximumOption = maximumOption;
        this.options = options;
    }

    public void addOption(MenuOption option) {
        this.options.add(option);
        option.setOptionGroup(this);
    }
    
    public void validate(OrderItem.OrderItemOptionGroup orderGroup) {
        if (!this.name.equals(orderGroup.getName())) {
            throw new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT);
        }
        for (OrderItem.OrderItemOption orderOption : orderGroup.getOrderItemOptions()) {
            validateOptionWith(orderOption);
        }
    }
    
    private void validateOptionWith(OrderItem.OrderItemOption orderOption) {
        this.options.stream()
                .filter(option -> option.getName().equals(orderOption.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT))
                .validate(orderOption);
    }
    
}
