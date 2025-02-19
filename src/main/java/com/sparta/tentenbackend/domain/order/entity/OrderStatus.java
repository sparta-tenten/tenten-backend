package com.sparta.tentenbackend.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    WAITING_ORDER_RECEIVE("WAITING_ORDER_RECEIVE"),
    ORDER_RECEIVED("ORDER_RECEIVED"),
    COOKING("COOKING"),
    DELIVERING("DELIVERING"),
    DELIVERY_COMPLETED("DELIVERY_COMPLETED"),
    CANCELLED("CANCELLED");

    private final String value;
}
