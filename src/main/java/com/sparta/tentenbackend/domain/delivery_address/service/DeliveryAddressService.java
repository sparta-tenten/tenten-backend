package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;

public interface DeliveryAddressService {

    // TODO 유저 추가
    DeliveryAddress createDeliveryAddress(DeliveryAddressRequest req);
}
