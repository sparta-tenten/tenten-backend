package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.repository.DeliveryAddressRepository;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.region.service.TownService;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.BadRequestException;
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

    @Override
    @Transactional
    public DeliveryAddress createDeliveryAddress(CreateDeliveryAddressRequest req, User user) {
        Town town = townService.getTownByCode(req.getTownCode());
        DeliveryAddress deliveryAddress = new DeliveryAddress(req, town, user);

        return deliveryAddressRepository.save(deliveryAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeliveryAddress> getDeliveryList(User user, Pageable pageable) {
        return deliveryAddressRepository.findAllByUserId(user.getId(), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public DeliveryAddress getDeliveryAddressById(UUID id) {
        return deliveryAddressRepository.findDeliveryAddressById(id);
    }

    @Override
    @Transactional
    public DeliveryAddress updateDeliveryAddress(UpdateDeliveryAddressRequest req, User user) {
        DeliveryAddress deliveryAddress = getDeliveryAddressById(req.getId());

        if (!deliveryAddress.getUser().getId().equals(user.getId())) {
            throw new BadRequestException();
        }

        Town town = townService.getTownByCode(req.getTownCode());
        deliveryAddress.update(req, town);

        return deliveryAddress;
    }

    @Override
    @Transactional
    public void deleteDeliveryAddressById(UUID id, User user) {
        DeliveryAddress deliveryAddress = getDeliveryAddressById(id);

        if (deliveryAddress.isDeleted() ||
            !deliveryAddress.getUser().getId().equals(user.getId())) {
            throw new BadRequestException();
        }

        deliveryAddress.setDeleted(true);
    }
}
