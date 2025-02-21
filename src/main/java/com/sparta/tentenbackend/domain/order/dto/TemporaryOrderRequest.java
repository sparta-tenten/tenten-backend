package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.global.annotation.ValidEnum;
import com.sparta.tentenbackend.global.annotation.ValidUUID;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TemporaryOrderRequest {

    @ValidEnum(enumClass = DeliveryType.class)
    private DeliveryType deliveryType;
    @ValidUUID
    private UUID storeId;
}
