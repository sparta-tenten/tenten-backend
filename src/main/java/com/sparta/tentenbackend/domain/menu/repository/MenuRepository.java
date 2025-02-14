package com.sparta.tentenbackend.domain.menu.repository;
import com.sparta.tentenbackend.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface MenuRepository extends JpaRepository<Menu, UUID> {
}


