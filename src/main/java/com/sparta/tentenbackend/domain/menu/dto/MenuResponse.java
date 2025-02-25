package com.sparta.tentenbackend.domain.menu.dto;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuStatus;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class MenuResponse {

    private UUID menuId;
    private String name;
    private Long price;
    private String description;
    private MenuStatus status;
    private List<OptionResponse> optionResponseList;

    public MenuResponse(Menu menu) {
        this.menuId = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
        this.status = menu.getStatus();
        this.optionResponseList = menu.getMenuOptionList().stream().map(OptionResponse::new)
            .collect(Collectors.toList());
    }

}
