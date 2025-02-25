package com.sparta.tentenbackend.domain.ai.repository;

import com.sparta.tentenbackend.domain.ai.entity.Ai;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiRepository extends JpaRepository<Ai, UUID> {

}
