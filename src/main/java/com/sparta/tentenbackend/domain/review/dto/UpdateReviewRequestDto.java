package com.sparta.tentenbackend.domain.review.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UpdateReviewRequestDto {
  // 리뷰 ID
  private String id;
  // 리뷰 내용
  private String content;
  // 리뷰 평점 1~5
  private int grade;
  // 리뷰 이미지
  private MultipartFile file;

  public UpdateReviewRequestDto(String id, String content, int grade, MultipartFile file) {
    this.id = id;
    this.content = content;
    this.grade = grade;
    this.file = file;
  }

//  // 회원 ID
//  private int userId;

//  // 주문 ID
//  private String orderId;

}
