package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.CreateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.UpdateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.user.entity.User;
import java.io.IOException;
import org.springframework.data.domain.Page;

public interface ReviewService {

  ReviewResponseDto addReview(CreateReviewRequestDto requestDto, User user) throws IOException;

  Page<ReviewResponseDto> findAllReviews(User user, int page, int size, String sortBy, boolean isAsc);

  ReviewResponseDto modifyReview(UpdateReviewRequestDto requestDto, User user) throws IOException;

  void removeReview(String reviewId, User user);
}

