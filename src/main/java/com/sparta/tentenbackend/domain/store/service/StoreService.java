package com.sparta.tentenbackend.domain.store.service;

import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.category.repository.CategoryRepository;
import com.sparta.tentenbackend.domain.store.dto.StoreRequestDto;
import com.sparta.tentenbackend.domain.store.dto.StoreResponseDto;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.store.repository.StoreRepository;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import com.sparta.tentenbackend.global.exception.UnauthorizedException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    // 가게 생성
    @Transactional
    public StoreResponseDto createStore(StoreRequestDto requestDto, User user) {
        // 카테고리 ID를 통해 카테고리 엔티티 조회
        Category category = categoryRepository.findById(requestDto.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Category not found"));

        Store store = new Store(
            UUID.randomUUID(),
            requestDto.getName(),
            requestDto.getAddress(),
            requestDto.getPhoneNumber(),
            requestDto.getImage(),
            user,
            category,
            requestDto.getTown()
        );

        storeRepository.save(store);
        return new StoreResponseDto(store);
    }

    // 가게 조회
    @Transactional(readOnly = true)
    public StoreResponseDto getStore(UUID storeId) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new NotFoundException("해당 가게를 찾을 수 없습니다."));
        return new StoreResponseDto(store);
    }

    // 가게 수정
    @Transactional
    public StoreResponseDto updateStore(UUID storeId, StoreRequestDto requestDto, User user) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new NotFoundException("해당 가게를 찾을 수 없습니다."));

        if (!store.getUser().equals(user)) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        store.setName(requestDto.getName());
        store.setAddress(requestDto.getAddress());
        store.setPhoneNumber(requestDto.getPhoneNumber());
        store.setImage(requestDto.getImage());

        return new StoreResponseDto(store);
    }

    // 가게 삭제
    @Transactional
    public void deleteStore(UUID storeId, User user) {
        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new NotFoundException("해당 가게를 찾을 수 없습니다."));

        if (!store.getUser().equals(user)) {
            throw new UnauthorizedException("권한이 없습니다.");
        }

        storeRepository.delete(store);
    }
}
