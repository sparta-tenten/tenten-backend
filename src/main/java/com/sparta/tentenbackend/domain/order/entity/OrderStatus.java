package com.sparta.tentenbackend.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PAYMENT_COMPLETED("PAYMENT_COMPLETED"),
    ORDER_RECEIVED("ORDER_RECEIVED"),
    COOKING("COOKING"),
    DELIVERING("DELIVERING"),
    DELIVERY_COMPLETED("DELIVERY_COMPLETED"),
    CANCELLED("CANCELLED");

    private final String value;
}
