package com.sparta.tentenbackend.domain.payment.service;

import com.sparta.tentenbackend.domain.payment.dto.PaymentRequest;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import java.util.UUID;

public interface PaymentService {

    Payment createPayment(PaymentRequest paymentRequest);

    boolean isPaymentCompleted(UUID orderId);

    Payment getPaymentByOrderId(UUID orderId);
}
