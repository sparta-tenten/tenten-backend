package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.entity.OrderType;
import java.util.UUID;
import lombok.Getter;

@Getter
public class TemporaryOrderResponse {

    private final DeliveryType deliveryType;
    private final OrderType orderType;
    private final OrderStatus orderStatus;
    private final UUID storeId;
    private final UUID orderId;

    public TemporaryOrderResponse(Order order) {
        this.deliveryType = order.getDeliveryType();
        this.orderType = order.getOrderType();
        this.storeId = order.getStore().getId();
        this.orderStatus = order.getOrderStatus();
        this.orderId = order.getId();
    }
}
