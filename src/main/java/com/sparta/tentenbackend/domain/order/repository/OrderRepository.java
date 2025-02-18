package com.sparta.tentenbackend.domain.order.repository;

import com.sparta.tentenbackend.domain.order.entity.Order;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAll(Pageable pageable);
}
