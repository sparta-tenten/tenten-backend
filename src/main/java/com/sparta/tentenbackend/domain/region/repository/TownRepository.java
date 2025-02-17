package com.sparta.tentenbackend.domain.region.repository;

import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, String> {

    default Town findTownByCode(String code) {
        return findByCode(code).orElseThrow(
            () -> new NotFoundException("존재하지 않는 법정동 코드입니다.")
        );
    }

    Optional<Town> findByCode(String code);
}
