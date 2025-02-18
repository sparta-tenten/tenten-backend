package com.sparta.tentenbackend.domain.review.dto;

import com.sparta.tentenbackend.domain.review.entity.OwnerReview;
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

  public OwnerReviewResponseDto(OwnerReview ownerReview) {
    this.id = ownerReview.getId().toString();
    this.content = ownerReview.getContent();
    this.reviewId = ownerReview.getReview().getId().toString();
  }
}
