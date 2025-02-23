package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.dto.TemporaryOrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface OrderRepositoryService {

    Page<Order> getOrderList(OrderSearchRequest orderSearchRequest);

    Order getOrderById(UUID orderId);

    Page<Order> getOrderListByStoreId(UUID storeId, OrderSearchRequest orderSearchRequest);

    Order createTemporaryOrder(TemporaryOrderRequest req);
}
