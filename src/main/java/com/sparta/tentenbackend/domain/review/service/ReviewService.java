package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.CreateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.UpdateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import java.io.IOException;
import org.springframework.data.domain.Page;

public interface ReviewService {

  ReviewResponseDto addReview(CreateReviewRequestDto requestDto) throws IOException;

  Page<ReviewResponseDto> findAllReviews(int page, int size, String sortBy, boolean isAsc);

  ReviewResponseDto modifyReview(UpdateReviewRequestDto requestDto) throws IOException;

  void removeReview(String reviewId);
}
