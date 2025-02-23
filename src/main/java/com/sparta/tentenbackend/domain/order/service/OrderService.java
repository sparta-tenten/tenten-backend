package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderMenuRequest;
import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.user.entity.User;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order orderForCustomer(OrderRequest req);

    void cancelOrder(UUID orderId);

    void updateOrderStatus(UUID orderId, User owner);

    void setMenuList(Order order, List<OrderMenuRequest> orderMenuRequests);

    void acceptOrder(UUID orderId, User owner);

    void rejectOrder(UUID orderId, User owner);

    void checkOwner(Order order, User owner);
}
