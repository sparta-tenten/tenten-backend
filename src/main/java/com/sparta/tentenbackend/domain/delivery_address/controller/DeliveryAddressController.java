package com.sparta.tentenbackend.domain.delivery_address.controller;

import com.sparta.tentenbackend.domain.delivery_address.dto.CreateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.dto.DeliveryAddressResponse;
import com.sparta.tentenbackend.domain.delivery_address.dto.UpdateDeliveryAddressRequest;
import com.sparta.tentenbackend.domain.delivery_address.entity.DeliveryAddress;
import com.sparta.tentenbackend.domain.delivery_address.service.DeliveryAddressService;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import com.sparta.tentenbackend.global.util.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Delivery Address", description = "배송지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    @PostMapping
    @Operation(summary = "배송지 추가하기")
    public ResponseEntity<DeliveryAddressResponse> addDeliveryAddress(
        @RequestBody @Valid CreateDeliveryAddressRequest req,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        DeliveryAddress deliveryAddress = deliveryAddressService.createDeliveryAddress(req,
            userDetails.getUser());

        return ResponseEntity.ok(new DeliveryAddressResponse(deliveryAddress));
    }

    @GetMapping
    @Operation(summary = "배송지 목록 조회")
    public ResponseEntity<Page<DeliveryAddressResponse>> getAllDeliveryAddress(
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "10", required = false) int size,
        @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortDirection,
        @RequestParam(defaultValue = "UPDATED_AT", required = false) PageUtils.CommonSortBy sortBy,
        @RequestParam(required = false) String keyword,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Page<DeliveryAddress> deliveryAddressList = deliveryAddressService.getDeliveryList(
            userDetails.getUser(),
            PageUtils.pageable(page, size), sortDirection, sortBy, keyword);

        return ResponseEntity.ok(deliveryAddressList.map(DeliveryAddressResponse::new));
    }

    @PutMapping
    @Operation(summary = "배송지 수정")
    public ResponseEntity<DeliveryAddressResponse> updateDeliveryAddress(
        @RequestBody @Valid UpdateDeliveryAddressRequest req,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        DeliveryAddress deliveryAddress = deliveryAddressService.updateDeliveryAddress(req,
            userDetails.getUser());

        return ResponseEntity.ok(new DeliveryAddressResponse(deliveryAddress));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "배송지 삭제")
    public ResponseEntity<String> deleteDeliveryAddress(
        @PathVariable UUID id,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        deliveryAddressService.deleteDeliveryAddressById(id, userDetails.getUser());

        return ResponseEntity.ok("배송지 삭제에 성공하였습니다.");
    }
}
