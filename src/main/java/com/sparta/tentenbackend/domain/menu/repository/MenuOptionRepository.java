
package com.sparta.tentenbackend.domain.menu.repository;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface MenuOptionRepository extends JpaRepository<MenuOption, UUID> {
}

