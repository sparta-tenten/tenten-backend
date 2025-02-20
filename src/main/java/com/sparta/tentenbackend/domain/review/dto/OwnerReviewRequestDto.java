package com.sparta.tentenbackend.domain.review.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerReviewRequestDto {
  // 사장님의 리뷰 ID
  private String id;
  // 사장님의 리뷰 내용
  private String content;

  // 리뷰 ID
  private String reviewId;

  // ID 데이터타입 String to UUID
  public UUID getId() {
    try {
      return UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
