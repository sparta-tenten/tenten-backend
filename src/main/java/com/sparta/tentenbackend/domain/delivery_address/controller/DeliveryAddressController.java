package com.sparta.tentenbackend.domain.delivery_address.controller;

import com.sparta.tentenbackend.domain.delivery_address.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;
}
