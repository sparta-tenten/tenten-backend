package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.repository.DeliveryAddressRepository;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.region.service.TownService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public DeliveryAddress createDeliveryAddress(CreateDeliveryAddressRequest req) {
        Town town = townService.getTownByCode(req.getTownCode());
        DeliveryAddress deliveryAddress = new DeliveryAddress(req, town);

        return deliveryAddressRepository.save(deliveryAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryAddress> getDeliveryList(Pageable pageable) {
        return deliveryAddressRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public DeliveryAddress getDeliveryAddressById(UUID id) {
        return deliveryAddressRepository.findDeliveryAddressById(id);
    }

    @Override
    @Transactional
    public DeliveryAddress updateDeliveryAddress(UpdateDeliveryAddressRequest req) {
        DeliveryAddress deliveryAddress = getDeliveryAddressById(req.getId());
        // TODO 유저 검증 로직 추가
        Town town = townService.getTownByCode(req.getTownCode());
        deliveryAddress.update(req, town);

        return deliveryAddress;
    }

    @Override
    @Transactional
    public void deleteDeliveryAddressById(UUID id) {
        DeliveryAddress deliveryAddress = getDeliveryAddressById(id);

        // TODO 유저 검증 로직 추가

        deliveryAddress.setDeleted(true);
    }

}
