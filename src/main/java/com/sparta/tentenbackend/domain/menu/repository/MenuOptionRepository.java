package com.sparta.tentenbackend.domain.menu.repository;

import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuOptionRepository extends JpaRepository<MenuOption, UUID> {

    // isDeleted = false 인 항목만 조회
    List<MenuOption> findByMenuIdAndIsDeletedFalse(UUID menuId);

    // UUID 리스트로 조회
    List<MenuOption> findAllByIdIn(List<UUID> idList);
}
