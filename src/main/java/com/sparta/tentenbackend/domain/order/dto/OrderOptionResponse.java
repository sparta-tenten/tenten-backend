package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderOptionResponse {

    private final UUID id;
    private final String name;
    private final Long price;

    public OrderOptionResponse(MenuOption menuOption) {
        this.id = menuOption.getId();
        this.name = menuOption.getName();
        this.price = menuOption.getPrice();
    }
}
