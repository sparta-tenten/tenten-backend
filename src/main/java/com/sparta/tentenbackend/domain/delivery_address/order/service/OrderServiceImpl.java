package com.sparta.tentenbackend.domain.delivery_address.order.service;

import com.sparta.tentenbackend.domain.delivery_address.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
}
