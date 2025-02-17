package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryAddressService {

    // TODO 유저 추가
    DeliveryAddress createDeliveryAddress(CreateDeliveryAddressRequest req);

    Page<DeliveryAddress> getDeliveryList(Pageable pageable);

    DeliveryAddress updateDeliveryAddress(UpdateDeliveryAddressRequest req);

    DeliveryAddress getDeliveryAddressById(UUID id);
}
