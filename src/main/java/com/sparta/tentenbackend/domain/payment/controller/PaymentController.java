package com.sparta.tentenbackend.domain.payment.controller;

import com.sparta.tentenbackend.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
}

