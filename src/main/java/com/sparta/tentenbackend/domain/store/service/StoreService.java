package com.sparta.tentenbackend.domain.store.service;

import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.category.repository.CategoryRepository;
import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.region.service.TownService;
import com.sparta.tentenbackend.domain.store.dto.StoreRequestDto;
import com.sparta.tentenbackend.domain.store.dto.StoreResponseDto;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.store.repository.StoreRepository;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import com.sparta.tentenbackend.global.exception.UnauthorizedException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final TownService townService;

    // 가게 생성
    @Transactional
    public StoreResponseDto createStore(StoreRequestDto requestDto, User user) {
        // 카테고리 ID를 통해 카테고리 엔티티 조회
        Category category = categoryRepository.findById(requestDto.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Category not found"));

        Town town = townService.getTownByCode(requestDto.getTownCode());

        Store store = new Store(
            requestDto.getName(),
            requestDto.getAddress(),
            requestDto.getPhoneNumber(),
            requestDto.getImage(),
            user,
            category,
            town
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

    @Transactional(readOnly = true)
    public Store getStoreById(UUID storeId) {
        return storeRepository.findById(storeId)
            .orElseThrow(() -> new NotFoundException("해당 가게를 찾을 수 없습니다."));
    }

    // 스토어 검색/정렬 메서드
    public Page<StoreResponseDto> searchStores(String keyword, UUID categoryId, String townCode,
        int page, int size, String sortBy, boolean isAsc) {

        Pageable pageable = PageRequest.of(page, size,
            isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        // 동적 쿼리 생성
        Specification<Store> spec = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(root.get("name"), "%" + keyword + "%"));
        }

        if (categoryId != null) {
            spec = spec.and(
                (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId));
        }

        if (townCode != null && !townCode.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("town").get("code"), townCode));
        }

        // 조회 및 DTO 변환
        Page<Store> storePage = storeRepository.findAll(spec, pageable);
        return storePage.map(StoreResponseDto::new);
    }

    public Page<StoreResponseDto> findStoresByCategory(String categoryId, String storeName,
        String townCode, int page, int size, String sortBy, boolean isAsc) {
        if (categoryId == null) {
            throw new BadRequestException("카테고리를 선택하세요.");
        }
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, getValidPageSize(size), sort);
        Page<Store> stores = storeRepository.findStoresByCategory(UUID.fromString(categoryId),
            storeName, townCode, sortBy, isAsc, pageable);
        return stores.map(StoreResponseDto::new);
    }

    // 페이지 노출 건수
    private int getValidPageSize(int size) {
        // 허용되는 페이지 크기 목록
        List<Integer> allowedSizes = Arrays.asList(10, 30, 50);
        // 페이지 크기가 허용된 값에 없으면 기본 10으로 설정
        if (!allowedSizes.contains(size)) {
            return 10;
        }
        return size;
    }

}
