package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderMenuRequest;
import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order orderForCustomer(OrderRequest req);

    void cancelOrder(UUID orderId);

    void updateOrderStatus(UUID orderId);

    void setMenuList(Order order, List<OrderMenuRequest> orderMenuRequests);
}
