package com.sparta.tentenbackend.domain.delivery_address.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryType {
    DELIVERY("DELIVERY"),
    TAKEOUT("TAKEOUt");

    private final String value;
}
