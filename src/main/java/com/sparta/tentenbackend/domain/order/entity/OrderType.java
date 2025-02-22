package com.sparta.tentenbackend.domain.order.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");

    private final String value;
}
