package com.sparta.tentenbackend.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequestDto { // 리뷰 만들기 request
  // 리뷰 내용
  private String content;
  // 리뷰 평점 1~5
  @Min(value = 1, message = "평점은 1점 이상 5점 이하로 입력해야 합니다.")
  @Max(value = 5, message = "평점은 1점 이상 5점 이하로 입력해야 합니다.")
  private int grade;
  // 리뷰 이미지
  private MultipartFile file;

  public CreateReviewRequestDto(String id, String content, int grade, MultipartFile file) {
    this.content = content;
    this.grade = grade;
    this.file = file;
  }

//  // 회원 ID
//  private int userId;

//  // 주문 ID
//  private String orderId;

}
