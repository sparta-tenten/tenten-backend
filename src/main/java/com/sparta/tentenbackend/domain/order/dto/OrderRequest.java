package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.OrderType;
import com.sparta.tentenbackend.global.annotation.ValidEnum;
import com.sparta.tentenbackend.global.annotation.ValidUUID;
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
    @ValidEnum(enumClass = DeliveryType.class)
    private DeliveryType deliveryType;
    @ValidEnum(enumClass = OrderType.class)
    private OrderType orderType;
    private UUID orderId;
    @ValidUUID
    private UUID storeId;
    private String deliveryAddress;
    private String phoneNumber;
    private String request;
}
