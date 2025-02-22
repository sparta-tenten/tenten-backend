package com.sparta.tentenbackend.domain.payment.entity;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.payment.dto.PaymentRequest;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "p_payment")
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentTime;
    private String buyerName;
    private String phoneNumber;
    private PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private Order order;

    public Payment(PaymentRequest paymentRequest, Order order) {
        this.amount = paymentRequest.getAmount();
        this.buyerName = paymentRequest.getBuyerName();
        this.phoneNumber = paymentRequest.getPhoneNumber();
        this.paymentMethod = paymentRequest.getPaymentMethod();
        this.paymentTime = LocalDateTime.now();
        this.order = order;
        this.paymentStatus = PaymentStatus.COMPLETED;
    }
}
