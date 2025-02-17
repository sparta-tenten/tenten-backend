package com.sparta.tentenbackend.domain.menu.repository;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface MenuOrderRepository extends JpaRepository<MenuOrder, UUID>{
}
