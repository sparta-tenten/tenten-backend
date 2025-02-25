package com.sparta.tentenbackend.domain.payment.service;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.service.OrderRepositoryService;
import com.sparta.tentenbackend.domain.payment.dto.PaymentRequest;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
import com.sparta.tentenbackend.domain.payment.entity.PaymentStatus;
import com.sparta.tentenbackend.domain.payment.repository.PaymentRepository;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepositoryService orderRepositoryService;

    @Override
    @Transactional
    public Payment createPayment(PaymentRequest paymentRequest) {
        Order order = orderRepositoryService.getOrderById(paymentRequest.getOrderId());
        order.setOrderStatus(OrderStatus.PAYMENT_COMPLETED);

        Payment payment = Payment.createCompletedPayment(paymentRequest, order);
        return paymentRepository.save(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isPaymentCompleted(UUID orderId) {
        Payment payment = getPaymentByOrderId(orderId);

        return payment.getPaymentStatus().equals(PaymentStatus.COMPLETED);
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByOrderId(UUID orderId) {
        return paymentRepository.findByOrderId(orderId).orElseThrow(
            () -> new NotFoundException("해당 주문 아이디에 대한 결제 내역이 존재하지 않습니다.")
        );
    }
}
