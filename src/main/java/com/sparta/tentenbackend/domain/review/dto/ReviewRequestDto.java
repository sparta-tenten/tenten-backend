package com.sparta.tentenbackend.domain.review.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
  // 리뷰 ID
  private String id;
  // 리뷰 내용
  private String content;
  // 리뷰 평점 1~5
  private int grade;
  // 리뷰 이미지 URL
  private String image;

//  // 회원 ID
//  private int userId;
//
//  // 주문 ID
//  private String orderId;

  // ID 데이터타입 String to UUID
  public UUID getId() {
    try {
      return UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
