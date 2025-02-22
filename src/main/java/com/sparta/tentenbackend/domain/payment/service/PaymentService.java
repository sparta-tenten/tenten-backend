package com.sparta.tentenbackend.domain.payment.service;

import com.sparta.tentenbackend.domain.payment.dto.PaymentRequest;
import com.sparta.tentenbackend.domain.payment.entity.Payment;

public interface PaymentService {

    Payment createPayment(PaymentRequest paymentRequest);
}
