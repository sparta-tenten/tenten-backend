package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Page;

public interface ReviewService {

  ReviewResponseDto addReview(ReviewRequestDto requestDto);

  Page<ReviewResponseDto> findAllReviews(int page, int size, String sortBy, boolean isAsc);

  ReviewResponseDto modifyReview(ReviewRequestDto requestDto);

  void removeReview(String reviewId);
}
