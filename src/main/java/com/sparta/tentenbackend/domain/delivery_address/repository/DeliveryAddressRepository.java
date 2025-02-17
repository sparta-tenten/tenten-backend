package com.sparta.tentenbackend.domain.delivery_address.repository;

import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, UUID> {

    Page<DeliveryAddress> findAll(Pageable pageable);
}
