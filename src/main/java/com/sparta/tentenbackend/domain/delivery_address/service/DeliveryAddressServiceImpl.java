package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.repository.DeliveryAddressRepository;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.region.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final TownService townService;

    // TODO 유저 추가
    @Override
    @Transactional
    public DeliveryAddress createDeliveryAddress(DeliveryAddressRequest req) {
        Town town = townService.getTownByCode(req.getTownCode());
        DeliveryAddress deliveryAddress = new DeliveryAddress(req, town);

        return deliveryAddressRepository.save(deliveryAddress);
    }
}
