
package com.sparta.tentenbackend.domain.menu.repository;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface MenuOptionRepository extends JpaRepository<MenuOption, UUID> {
  // 특정 메뉴 ID로 메뉴 옵션 조회
  List<MenuOption> findByMenuId(UUID menuId);
}

