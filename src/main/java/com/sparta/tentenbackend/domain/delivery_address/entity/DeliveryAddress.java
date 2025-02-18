package com.sparta.tentenbackend.domain.delivery_address.entity;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    private String detailAddress;

    @OneToOne(mappedBy = "deliveryAddress", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_code", nullable = false)
    private Town town;

    // TODO 유저 추가
    public DeliveryAddress(CreateDeliveryAddressRequest req, Town town) {
        this.name = req.getName();
        this.address = req.getAddress();
        this.detailAddress = req.getDetailAddress();
        this.town = town;
    }

}
