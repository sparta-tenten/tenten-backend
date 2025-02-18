package com.sparta.tentenbackend.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerReviewResponseDto {
  // 사장님의 리뷰 ID
  private String id;
  // 사장님의 리뷰 내용
  private String content;

  // 리뷰 ID
  private String reviewId;
}
