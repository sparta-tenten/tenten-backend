package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.user.entity.User;
import java.util.UUID;

public interface OrderService {

    Order orderForCustomer(UUID orderId, OrderRequest req, User user);

    void cancelOrder(UUID orderId, User user);

    void updateOrderStatus(UUID orderId);

    void acceptOrder(UUID orderId);

    void rejectOrder(UUID orderId);

    Order deliveryOrderForOwner(OrderRequest req, User owner);

    Order pickupOrderForOwner(OrderRequest req, User owner);

    void cancelOrderForOwner(UUID orderId, User owner);
}
