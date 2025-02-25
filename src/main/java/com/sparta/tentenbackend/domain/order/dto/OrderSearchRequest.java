package com.sparta.tentenbackend.domain.order.dto;

import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.global.util.PageUtils.CommonSortBy;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public class OrderSearchRequest {

    private UUID categoryId;
    private DeliveryType deliveryType;
    private OrderStatus orderStatus;
    private String keyword;
    private int page;
    private int size;
    private Sort.Direction sortDirection;
    private CommonSortBy sortBy;

    public OrderSearchRequest(DeliveryType deliveryType, OrderStatus orderStatus, String keyword,
        int page, int size, Sort.Direction sortDirection, CommonSortBy sortBy) {
        this.deliveryType = deliveryType;
        this.orderStatus = orderStatus;
        this.keyword = keyword;
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
        this.sortBy = sortBy;
    }
}
