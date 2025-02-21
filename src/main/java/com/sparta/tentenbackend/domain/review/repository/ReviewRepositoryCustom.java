package com.sparta.tentenbackend.domain.review.repository;

import com.sparta.tentenbackend.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
  Page<Review> findReviewsByStoreName(Long userId, String keyword, Pageable pageable);
  Page<Review> findReviewsByMenuName(Long userId, String keyword, Pageable pageable);
}

