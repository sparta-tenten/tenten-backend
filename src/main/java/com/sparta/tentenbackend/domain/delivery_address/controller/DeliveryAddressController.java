package com.sparta.tentenbackend.domain.delivery_address.controller;

import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressResponse;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.service.DeliveryAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Delivery Address", description = "배송지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    // TODO AuthenticationPrincipal 추가
    @PostMapping
    @Operation(summary = "배송지 추가하기")
    public ResponseEntity<DeliveryAddressResponse> addDeliveryAddress(
        @RequestBody @Valid DeliveryAddressRequest req) {
        DeliveryAddress deliveryAddress = deliveryAddressService.createDeliveryAddress(req);

        return ResponseEntity.ok(new DeliveryAddressResponse(deliveryAddress));
    }

    @GetMapping
    @Operation(summary = "배송지 목록 조회")
    public ResponseEntity<Page<DeliveryAddressResponse>> getAllDeliveryAddress(Pageable pageable) {
        Page<DeliveryAddress> deliveryAddressList = deliveryAddressService.findAllDeliveryAddresses(
            pageable);

        return ResponseEntity.ok(deliveryAddressList.map(DeliveryAddressResponse::new));
    }
}
