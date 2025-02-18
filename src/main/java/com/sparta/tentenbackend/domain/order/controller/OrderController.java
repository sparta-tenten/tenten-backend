package com.sparta.tentenbackend.domain.order.controller;

import com.sparta.tentenbackend.domain.order.dto.OrderResponse;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "주문 내역 조회하기")
    public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {
        Page<Order> orderList = orderService.getOrderList(pageable);

        return ResponseEntity.ok(orderList.map(OrderResponse::new));
    }
}
