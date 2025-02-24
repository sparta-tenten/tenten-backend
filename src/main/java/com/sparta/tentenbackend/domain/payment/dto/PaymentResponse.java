package com.sparta.tentenbackend.domain.payment.dto;

import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.payment.entity.PaymentMethod;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class PaymentResponse {

    private final UUID paymentId;
    private final String buyerName;
    private final String phoneNumber;
    private final PaymentMethod paymentMethod;
    private final Long amount;
    private final LocalDateTime paymentTime;

    public PaymentResponse(Payment payment) {
        this.paymentId = payment.getId();
        this.buyerName = payment.getBuyerName();
        this.phoneNumber = payment.getPhoneNumber();
        this.paymentMethod = payment.getPaymentMethod();
        this.amount = payment.getAmount();
        this.paymentTime = payment.getPaymentTime();
    }
}
