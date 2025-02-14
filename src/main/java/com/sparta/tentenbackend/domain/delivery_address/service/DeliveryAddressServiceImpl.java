package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.repository.DeliveryAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;
}
