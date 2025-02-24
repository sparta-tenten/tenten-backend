package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.user.entity.User;
import java.util.UUID;

public interface OrderService {

    Order orderForCustomer(OrderRequest req, User user);

    void cancelOrder(UUID orderId, User user);

    void updateOrderStatus(UUID orderId, User owner);

    void acceptOrder(UUID orderId, User owner);

    void rejectOrder(UUID orderId, User owner);

    void checkOwner(Order order, User owner);

    Order deliveryOrderForOwner(OrderRequest req, User owner);

    Order pickupOrderForOwner(OrderRequest req, User owner);

    void cancelOrderForOwner(UUID orderId, User owner);
}
