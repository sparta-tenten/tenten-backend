package com.sparta.tentenbackend.domain.order.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {
    ONLINE("ONLINE"),
    PHONE_CALL("PHONE_CALL");

    private final String value;
}
