
package com.sparta.tentenbackend.domain.menu.repository;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface MenuOptionRepository extends JpaRepository<MenuOption, UUID> {
  // is Deleted = false 인 항목만 조회
  List<MenuOption> findByMenuIdAndIsDeletedFalse(UUID menuId);
}

