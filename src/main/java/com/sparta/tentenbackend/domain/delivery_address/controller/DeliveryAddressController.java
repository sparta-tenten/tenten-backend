package com.sparta.tentenbackend.domain.delivery_address.controller;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressResponse;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.service.DeliveryAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        @RequestBody @Valid CreateDeliveryAddressRequest req) {
        DeliveryAddress deliveryAddress = deliveryAddressService.createDeliveryAddress(req);

        return ResponseEntity.ok(new DeliveryAddressResponse(deliveryAddress));
    }

    @GetMapping
    @Operation(summary = "배송지 목록 조회")
    public ResponseEntity<Page<DeliveryAddressResponse>> getAllDeliveryAddress(Pageable pageable) {
        Page<DeliveryAddress> deliveryAddressList = deliveryAddressService.getDeliveryList(
            pageable);

        return ResponseEntity.ok(deliveryAddressList.map(DeliveryAddressResponse::new));
    }

    // TODO AuthenticationPrincipal 추가
    @PutMapping
    @Operation(summary = "배송지 수정")
    public ResponseEntity<DeliveryAddressResponse> updateDeliveryAddress(
        @RequestBody @Valid UpdateDeliveryAddressRequest req
    ) {
        DeliveryAddress deliveryAddress = deliveryAddressService.updateDeliveryAddress(req);

        return ResponseEntity.ok(new DeliveryAddressResponse(deliveryAddress));
    }

    // TODO AuthenticationPrincipal 추가
    @DeleteMapping("/{id}")
    @Operation(summary = "배송지 삭제")
    public ResponseEntity<String> deleteDeliveryAddress(
        @PathVariable UUID id
    ) {
        deliveryAddressService.deleteDeliveryAddressById(id);

        return ResponseEntity.ok("배송지 삭제에 성공하였습니다.");
    }
}
