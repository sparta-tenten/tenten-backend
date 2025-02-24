package com.sparta.tentenbackend.domain.menu.repository;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    // Soft Delete 제외하고 Store에 속한 메뉴 조회
    List<Menu> findByStoreIdAndIsDeletedFalse(UUID storeId);

    // UUID 리스트로 메뉴 조회
    List<Menu> findAllByIdIn(List<UUID> idList);
}

