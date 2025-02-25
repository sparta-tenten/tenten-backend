package com.sparta.tentenbackend.domain.delivery_address.repository;

import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, UUID> {

    Page<DeliveryAddress> findAllByUserId(Long userId, Pageable pageable);

    default DeliveryAddress findDeliveryAddressById(UUID id) {
        return findById(id).orElseThrow(
            () -> new NotFoundException("존재하지 않는 배달지 주소 입니다.")
        );
    }
}
