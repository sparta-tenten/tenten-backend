
package com.sparta.tentenbackend.domain.menu.repository;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrderOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface MenuOrderOptionRepository extends JpaRepository<MenuOrderOption, UUID> {
}
