package com.sparta.tentenbackend.domain.menu.dto;

import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OptionResponse {

    private final UUID optionId;
    private final String name;
    private final Long price;

    public OptionResponse(MenuOption menuOption) {
        this.optionId = menuOption.getId();
        this.name = menuOption.getName();
        this.price = menuOption.getPrice();
    }
}
