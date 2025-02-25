package com.sparta.tentenbackend.domain.delivery_address.service;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.util.PageUtils.CommonSortBy;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public interface DeliveryAddressService {

    DeliveryAddress createDeliveryAddress(CreateDeliveryAddressRequest req, User user);

    Page<DeliveryAddress> getDeliveryList(User user, Pageable pageable, Direction sortDirection,
        CommonSortBy sortBy, String keyword);

    DeliveryAddress updateDeliveryAddress(UpdateDeliveryAddressRequest req, User user);

    DeliveryAddress getDeliveryAddressById(UUID id);

    void deleteDeliveryAddressById(UUID id, User user);
}
