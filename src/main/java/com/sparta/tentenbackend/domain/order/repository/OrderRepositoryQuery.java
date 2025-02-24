package com.sparta.tentenbackend.domain.order.repository;

import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.user.entity.User;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface OrderRepositoryQuery {

    Page<Order> getOrderList(OrderSearchRequest orderSearchRequest, User user);

    Page<Order> getOrderListByStoreId(UUID storeId, OrderSearchRequest orderSearchRequest);
}
