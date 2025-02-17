package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryAddressService {

    // TODO 유저 추가
    DeliveryAddress createDeliveryAddress(DeliveryAddressRequest req);

    Page<DeliveryAddress> findAllDeliveryAddresses(Pageable pageable);
}
