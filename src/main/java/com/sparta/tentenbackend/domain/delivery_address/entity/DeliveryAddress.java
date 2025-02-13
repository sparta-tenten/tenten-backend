package com.sparta.tentenbackend.domain.delivery_address.entity;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_delivery_address")
public class DeliveryAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToOne(mappedBy = "deliveryAddress", cascade = CascadeType.REMOVE, orphanRemoval = true, optional = false)
    private Order order;

    public DeliveryAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
