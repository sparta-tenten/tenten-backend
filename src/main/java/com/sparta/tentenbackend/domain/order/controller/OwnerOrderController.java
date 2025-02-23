package com.sparta.tentenbackend.domain.order.controller;

import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.dto.OrderResponse;
import com.sparta.tentenbackend.domain.order.dto.OrderSearchRequest;
import com.sparta.tentenbackend.domain.order.entity.DeliveryType;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.service.OrderRepositoryService;
import com.sparta.tentenbackend.domain.order.service.OrderService;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owner/order")
@Tag(name = "Owner Order", description = "사장님 주문 관련 API")
public class OwnerOrderController {

    private final OrderService orderService;
    private final OrderRepositoryService orderRepositoryService;

    // TODO AuthenticationPrincipal 추가
    @GetMapping("/{storeId}")
    @Operation(summary = "사장님 가게 주문 내역 조회하기")
    public ResponseEntity<Page<OrderResponse>> getOrderList(
        @PathVariable UUID storeId,
        @RequestParam(required = false) DeliveryType deliveryType,
        @RequestParam(required = false) OrderStatus orderStatus,
        @RequestParam(required = false) String keyword,
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "9", required = false) int size) {
        Page<Order> orderList = orderRepositoryService.getOrderListByStoreId(storeId,
            new OrderSearchRequest(deliveryType, orderStatus, keyword, page, size));
        return ResponseEntity.ok(orderList.map(OrderResponse::new));
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "주문 상태 변경")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable UUID orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.updateOrderStatus(orderId, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/accept/{orderId}")
    @Operation(summary = "주문 수락하기")
    public ResponseEntity<Void> acceptOrder(@PathVariable UUID orderId, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        orderService.acceptOrder(orderId, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reject/{orderId}")
    @Operation(summary = "주문 거부하기")
    public ResponseEntity<Void> rejectOrder(@PathVariable UUID orderId, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        orderService.rejectOrder(orderId, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delivery")
    @Operation(summary = "사장님의 주문 접수(배달)")
    public ResponseEntity<OrderResponse> deliveryOrder(@RequestBody OrderRequest orderRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Order order = orderService.deliveryOrderForOwner(orderRequest, userDetails.getUser());

        return ResponseEntity.ok(new OrderResponse(order));
    }

    @PostMapping("/pickup")
    @Operation(summary = "사장님의 주문 접수(포장)")
    public ResponseEntity<OrderResponse> pickupOrder(@RequestBody OrderRequest orderRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Order order = orderService.pickupOrderForOwner(orderRequest, userDetails.getUser());

        return ResponseEntity.ok(new OrderResponse(order));
    }

//    @PostMapping("/cancel/{orderId}")
//    @Operation(summary = "사장님 주문 취소하기")
//    public ResponseEntity<Void> cancelOrder(@PathVariable ValidUUID orderId) {
//        orderService.cancelOrder(orderId);
//        return ResponseEntity.ok().build();
//    }
}
