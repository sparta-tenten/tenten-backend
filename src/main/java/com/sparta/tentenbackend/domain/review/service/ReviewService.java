package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;

public interface ReviewService {

  ReviewResponseDto addReview(ReviewRequestDto requestDto);
}
