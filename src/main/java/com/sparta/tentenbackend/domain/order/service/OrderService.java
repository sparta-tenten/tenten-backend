package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> getOrderList(Pageable pageable);
}
