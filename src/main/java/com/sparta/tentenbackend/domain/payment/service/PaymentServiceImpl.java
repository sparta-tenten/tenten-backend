package com.sparta.tentenbackend.domain.payment.service;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.service.OrderService;
import com.sparta.tentenbackend.domain.payment.dto.PaymentRequest;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public Payment createPayment(PaymentRequest paymentRequest) {
        Order order = orderService.getOrderById(paymentRequest.getOrderId());

        Payment payment = new Payment(paymentRequest, order);
        return paymentRepository.save(payment);
    }
}
