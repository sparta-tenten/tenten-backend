package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.entity.OrderType;
import com.sparta.tentenbackend.domain.store.dto.StoreResponseDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class OrderResponse {

    private final UUID id;
    private final Long totalPrice;
    private final DeliveryType deliveryType;
    private final OrderType orderType;
    private final OrderStatus orderStatus;
    private final List<OrderMenuResponse> orderMenuResponseList;
    private final StoreResponseDto storeResponseDto;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.deliveryType = order.getDeliveryType();
        this.orderType = order.getOrderType();
        this.orderStatus = order.getOrderStatus();
        this.orderMenuResponseList = order.getMenuOrderList().stream().map(OrderMenuResponse::new)
            .collect(Collectors.toList());
        this.storeResponseDto = new StoreResponseDto(order.getStore());
    }
}
