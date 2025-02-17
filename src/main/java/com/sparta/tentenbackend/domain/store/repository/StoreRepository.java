package com.sparta.tentenbackend.domain.store.repository;
import com.sparta.tentenbackend.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
