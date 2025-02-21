package com.sparta.tentenbackend.domain.payment.dto;

import com.sparta.tentenbackend.global.annotation.ValidUUID;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PaymentRequest {

    @ValidUUID
    private UUID orderId;
    @NotNull
    private Long amount;

}
