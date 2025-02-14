package com.sparta.tentenbackend.domain.payment.repository;

import com.sparta.tentenbackend.domain.payment.entity.Payment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}
