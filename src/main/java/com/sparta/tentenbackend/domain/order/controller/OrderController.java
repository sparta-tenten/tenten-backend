package com.sparta.tentenbackend.domain.order.controller;

import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.dto.OrderResponse;
import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.dto.TemporaryOrderRequest;
import com.sparta.tentenbackend.domain.order.dto.TemporaryOrderResponse;
import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.service.OrderRepositoryService;
import com.sparta.tentenbackend.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "Order", description = "주문 관련 API")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepositoryService orderRepositoryService;

    // TODO AuthenticationPrincipal 추가
    @GetMapping
    @Operation(summary = "주문 내역 조회하기")
    public ResponseEntity<Page<OrderResponse>> getOrderList(
        @RequestParam(required = false) UUID categoryId,
        @RequestParam(required = false) DeliveryType deliveryType,
        @RequestParam(required = false) OrderStatus orderStatus,
        @RequestParam(required = false) String keyword,
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "9", required = false) int size) {
        Page<Order> orderList = orderRepositoryService.getOrderList(
            new OrderSearchRequest(categoryId, deliveryType, orderStatus, keyword, page, size));

        return ResponseEntity.ok(orderList.map(OrderResponse::new));
    }

    // TODO AuthenticationPrincipal 추가
    @PostMapping("/temp")
    @Operation(summary = "임시 주문 생성(결제 대기)")
    public ResponseEntity<TemporaryOrderResponse> createTemporaryOrder(
        @RequestBody @Valid TemporaryOrderRequest req) {
        Order order = orderRepositoryService.createTemporaryOrder(req);
        return ResponseEntity.ok(new TemporaryOrderResponse(order));
    }


    // TODO AuthenticationPrincipal 추가
    @PostMapping
    @Operation(summary = "주문하가")
    public ResponseEntity<OrderResponse> orderForCustomer(@RequestBody @Valid OrderRequest req) {
        return ResponseEntity.ok(new OrderResponse(orderService.orderForCustomer(req)));
    }

    // TODO AuthenticationPrincipal 추가
    @PostMapping("/cancel/{orderId}")
    @Operation(summary = "주문 취소하기")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
