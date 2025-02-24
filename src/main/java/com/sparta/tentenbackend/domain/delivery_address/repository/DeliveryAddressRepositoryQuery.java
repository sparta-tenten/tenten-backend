package com.sparta.tentenbackend.domain.delivery_address.repository;

import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.util.PageUtils.CommonSortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public interface DeliveryAddressRepositoryQuery {

    Page<DeliveryAddress> getOrderList(User user, Pageable pageable, Direction sortDirection,
        CommonSortBy sortBy, String keyword);
}
