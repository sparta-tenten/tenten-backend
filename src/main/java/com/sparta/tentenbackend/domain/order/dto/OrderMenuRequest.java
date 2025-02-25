package com.sparta.tentenbackend.domain.order.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderMenuRequest {

    @NotBlank
    private UUID id;
    @NotBlank
    private Long quantity;
    private List<UUID> optionIdList;
}
