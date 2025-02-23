package com.sparta.tentenbackend.domain.order.repository;

import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface OrderRepositoryQuery {

    Page<Order> getOrderList(OrderSearchRequest orderSearchRequest);

    Page<Order> getOrderListByStoreId(UUID storeId, OrderSearchRequest orderSearchRequest);
}
