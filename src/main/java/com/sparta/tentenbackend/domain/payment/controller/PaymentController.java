package com.sparta.tentenbackend.domain.payment.controller;

import com.sparta.tentenbackend.domain.payment.dto.PaymentRequest;
import com.sparta.tentenbackend.domain.payment.dto.PaymentResponse;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Tag(name = "Payment", description = "결제 관련 API")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "결제하기")
    public ResponseEntity<PaymentResponse> payment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.createPayment(paymentRequest);
        return ResponseEntity.ok(new PaymentResponse(payment));
    }
}

