package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.OwnerReviewResponseDto;
import com.sparta.tentenbackend.domain.user.entity.User;

public interface OwnerReviewService {

  OwnerReviewResponseDto addOwnerReview(OwnerReviewRequestDto requestDto, User user);

  OwnerReviewResponseDto modifyOwnerReview(OwnerReviewRequestDto requestDto);

  void removeOwnerReview(String ownerReviewId);
}
