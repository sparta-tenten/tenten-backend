package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> getOrderList(Pageable pageable);

    Order createOrder(OrderRequest req);

    void cancelOrder(UUID orderId);

    Order getOrderById(UUID orderId);

    Page<Order> getOrderListByStoreId(UUID storeId, Pageable pageable);
}
