package com.sparta.tentenbackend.domain.payment.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    WAITING("WAITING"),
    COMPLETED("COMPLETED"),
    CANCEL("CANCEL");

    private final String value;
}
