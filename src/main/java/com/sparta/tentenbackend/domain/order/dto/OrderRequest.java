package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {

    @NotEmpty
    @Size(min = 1)
    private List<OrderMenuRequest> orderMenuRequestList;
    @NotBlank
    private DeliveryType deliveryType;
    @NotBlank
    private OrderType orderType;
    @NotBlank
    private UUID storeId;
    private String deliveryAddress;
    private String phoneNumber;
    private String request;
}
