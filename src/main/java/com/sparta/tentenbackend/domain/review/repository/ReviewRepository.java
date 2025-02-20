package com.sparta.tentenbackend.domain.review.repository;

import com.sparta.tentenbackend.domain.review.entity.Review;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

  Page<Review> findAllByIsDeletedFalse(Pageable pageable);
}
