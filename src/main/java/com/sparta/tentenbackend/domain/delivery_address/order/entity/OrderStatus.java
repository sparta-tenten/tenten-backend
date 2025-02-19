package com.sparta.tentenbackend.domain.delivery_address.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDER_RECEIVED("ORDER_RECEIVED"),
    COOKING("COOKING"),
    DELIVERING("DELIVERING"),
    DELIVERY_COMPLETED("DELIVERY_COMPLETED"),
    CANCELLED("CANCELLED");

    private final String value;
}
