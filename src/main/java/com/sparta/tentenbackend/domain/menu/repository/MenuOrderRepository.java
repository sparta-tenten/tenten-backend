package com.sparta.tentenbackend.domain.menu.repository;

import com.sparta.tentenbackend.domain.menu.entity.MenuOrder;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, UUID> {

}
