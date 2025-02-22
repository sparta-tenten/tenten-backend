package com.sparta.tentenbackend.domain.review.repository;

import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
  Page<Review> findReviewsByStore(String storeId, String sortBy, boolean isAsc, Pageable pageable);
  Page<Review> findReviewsByStoreName(Long userId, String keyword, String sortBy, boolean isAsc, Pageable pageable);
  Page<Review> findReviewsByMenuName(Long userId, String keyword, String sortBy, boolean isAsc, Pageable pageable);
}

