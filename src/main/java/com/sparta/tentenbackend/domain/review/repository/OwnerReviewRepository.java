package com.sparta.tentenbackend.domain.review.repository;

import com.sparta.tentenbackend.domain.review.entity.OwnerReview;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerReviewRepository extends JpaRepository<OwnerReview, UUID> {

  OwnerReview findByReview_IdAndIsDeletedFalse(UUID reviewId);

  OwnerReview findByIdAndIsDeletedFalse(UUID id);
}
