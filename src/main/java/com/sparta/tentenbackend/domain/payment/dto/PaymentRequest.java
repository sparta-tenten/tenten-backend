package com.sparta.tentenbackend.domain.payment.dto;

import com.sparta.tentenbackend.domain.payment.entity.PaymentMethod;
import com.sparta.tentenbackend.global.annotation.ValidEnum;
import com.sparta.tentenbackend.global.annotation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PaymentRequest {

    @ValidUUID
    private UUID orderId;
    @NotNull
    private Long amount;
    @NotBlank
    private String buyerName;
    @NotBlank
    private String phoneNumber;
    @ValidEnum(enumClass = PaymentMethod.class)
    private PaymentMethod paymentMethod;
}
