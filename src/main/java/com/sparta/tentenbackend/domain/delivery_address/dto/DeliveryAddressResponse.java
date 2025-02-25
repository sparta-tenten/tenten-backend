package com.sparta.tentenbackend.domain.delivery_address.dto;

import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import java.util.UUID;
import lombok.Getter;

@Getter
public class DeliveryAddressResponse {

    private final UUID id;
    private final String name;
    private final String address;
    private final String detailAddress;
    private final String townCode;

    public DeliveryAddressResponse(DeliveryAddress deliveryAddress) {
        this.id = deliveryAddress.getId();
        this.name = deliveryAddress.getName();
        this.address = deliveryAddress.getAddress();
        this.detailAddress = deliveryAddress.getDetailAddress();
        this.townCode = deliveryAddress.getTown().getCode();
    }
}
