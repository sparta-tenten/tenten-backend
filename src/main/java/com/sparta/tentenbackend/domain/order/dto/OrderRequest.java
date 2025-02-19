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
    private String address;
    @NotBlank
    private DeliveryType deliveryType;
    @NotBlank
    private OrderType orderType;
    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String request;
    @NotBlank
    private UUID storeId;
}
