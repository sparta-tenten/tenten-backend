package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrder;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class OrderMenuResponse {

    private final UUID id;
    private final String name;
    private final Long price;
    private final Long quantity;
    private final List<OrderOptionResponse> orderOptionResponseList;

    public OrderMenuResponse(MenuOrder menuOrder) {
        Menu menu = menuOrder.getMenu();
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.quantity = menuOrder.getQuantity();
        this.orderOptionResponseList = menu.getMenuOptionList().stream()
            .map(OrderOptionResponse::new).collect(
                Collectors.toList());
    }
}
