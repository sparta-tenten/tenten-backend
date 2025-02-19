package com.sparta.tentenbackend.domain.order.controller;

import com.sparta.tentenbackend.domain.order.dto.OrderResponse;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owner/order")
@Tag(name = "Owner Order", description = "사장님 주문 관련 API")
public class OwnerOrderController {

    private final OrderService orderService;

    // TODO 필터링 & 검색 기능 추가
    // TODO AuthenticationPrincipal 추가
    @GetMapping("/{storeId}")
    @Operation(summary = "사장님 가게 주문 내역 조회하기")
    public ResponseEntity<Page<OrderResponse>> getOrderList(@PathVariable UUID storeId,
        Pageable pageable) {
        Page<Order> orderList = orderService.getOrderListByStoreId(storeId, pageable);
        return ResponseEntity.ok(orderList.map(OrderResponse::new));
    }

    @PostMapping("/cancel/{orderId}")
    @Operation(summary = "사장님 주문 취소하기")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
