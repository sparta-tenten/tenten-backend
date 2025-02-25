package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.menu.entity.MenuOrderOption;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderOptionResponse {

    private final UUID id;
    private final String name;
    private final Long price;

    public OrderOptionResponse(MenuOrderOption op) {
        this.id = op.getMenuOption().getId();
        this.name = op.getMenuOption().getName();
        this.price = op.getMenuOption().getPrice();
    }
}
