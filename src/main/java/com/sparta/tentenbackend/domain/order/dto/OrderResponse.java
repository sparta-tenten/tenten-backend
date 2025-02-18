package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.entity.OrderType;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderResponse {

    private final UUID id;
    private final Long totalPrice;
    private final DeliveryType deliveryType;
    private final OrderType orderType;
    private final OrderStatus orderStatus;
    // TODO List<MenuResponse> 추가하기
    // TODO PaymentResponse 추가하기

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.deliveryType = order.getDeliveryType();
        this.orderType = order.getOrderType();
        this.orderStatus = order.getOrderStatus();
    }
}
