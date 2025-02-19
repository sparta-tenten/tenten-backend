package com.sparta.tentenbackend.domain.delivery_address.order.repository;

import com.sparta.tentenbackend.domain.delivery_address.order.entity.Order;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
