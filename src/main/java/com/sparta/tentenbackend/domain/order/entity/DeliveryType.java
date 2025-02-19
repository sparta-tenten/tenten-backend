package com.sparta.tentenbackend.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryType {
    DELIVERY("DELIVERY"),
    TAKEOUT("TAKEOUt");

    private final String value;
}
