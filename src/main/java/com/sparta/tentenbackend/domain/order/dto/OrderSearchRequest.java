package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderSearchRequest {

    private UUID categoryId;
    private DeliveryType deliveryType;
    private OrderStatus orderStatus;
    private String keyword;
    private int page;
    private int size;

    public OrderSearchRequest(DeliveryType deliveryType, OrderStatus orderStatus, String keyword,
        int page, int size) {
        this.deliveryType = deliveryType;
        this.orderStatus = orderStatus;
        this.keyword = keyword;
        this.page = page;
        this.size = size;
    }
}
