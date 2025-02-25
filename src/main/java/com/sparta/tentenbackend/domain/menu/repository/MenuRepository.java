package com.sparta.tentenbackend.domain.menu.repository;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    // Soft Delete 제외하고 Store에 속한 메뉴 조회
    List<Menu> findByStoreIdAndIsDeletedFalse(UUID storeId);

    // UUID 리스트로 메뉴 조회
    List<Menu> findAllByIdIn(List<UUID> idList);

    // 검색 + 정렬 + 페이징을 위한 메서드
    Page<Menu> findByStoreIdAndDeletedFalseAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
        UUID storeId,
        String nameKeyword,
        String categoryKeyword,
        Pageable pageable
    );

}

