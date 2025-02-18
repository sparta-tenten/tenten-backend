package com.sparta.tentenbackend.domain.order.entity;

import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.payment.entity.Payment;
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
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "p_order")
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Long totalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private String deliveryAddress;

    public Order(Long totalPrice, DeliveryType deliveryType, OrderType orderType,
        OrderStatus orderStatus) {
        this.totalPrice = totalPrice;
        this.deliveryType = deliveryType;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
    }
}
