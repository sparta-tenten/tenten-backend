package com.sparta.tentenbackend.domain.region.repository;


import com.sparta.tentenbackend.domain.region.entity.Region;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {

}
