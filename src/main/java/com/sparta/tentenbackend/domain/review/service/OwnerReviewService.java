package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.OwnerReviewResponseDto;

public interface OwnerReviewService {

  OwnerReviewResponseDto addOwnerReview(OwnerReviewRequestDto requestDto);

  OwnerReviewResponseDto modifyOwnerReview(OwnerReviewRequestDto requestDto);

  void removeOwnerReview(String ownerReviewId);
}
