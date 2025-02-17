package com.sparta.tentenbackend.domain.region.repository;

import com.sparta.tentenbackend.domain.region.entity.Town;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, String> {

    Optional<Town> findByCode(String code);
}
