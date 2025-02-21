package com.sparta.tentenbackend.domain.store.controller;

import com.sparta.tentenbackend.domain.store.dto.StoreRequestDto;
import com.sparta.tentenbackend.domain.store.dto.StoreResponseDto;
import com.sparta.tentenbackend.domain.store.service.StoreService;
import com.sparta.tentenbackend.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(
        @RequestBody @Valid StoreRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(storeService.createStore(requestDto, userDetails.getUser()));
    }

    // 가게 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable UUID storeId) {
        return ResponseEntity.ok(storeService.getStore(storeId));
    }

    // 가게 수정
    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(
        @PathVariable UUID storeId,
        @RequestBody StoreRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(
            storeService.updateStore(storeId, requestDto, userDetails.getUser()));
    }

    // 가게 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(
        @PathVariable UUID storeId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        storeService.deleteStore(storeId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }


}
