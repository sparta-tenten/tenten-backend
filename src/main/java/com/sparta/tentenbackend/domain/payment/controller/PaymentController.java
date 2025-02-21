package com.sparta.tentenbackend.domain.payment.controller;

import com.sparta.tentenbackend.domain.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Tag(name = "Payment", description = "결제 관련 API")
public class PaymentController {

    private final PaymentService paymentService;

//    @PostMapping
//    @Operation(summary = "결제하기")
//    public ResponseEntity<Void> payment(@RequestBody Payment payment) {
//
//    }
}

