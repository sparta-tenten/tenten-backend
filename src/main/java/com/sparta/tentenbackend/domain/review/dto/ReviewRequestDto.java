package com.sparta.tentenbackend.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
  // 리뷰 이미지
  private MultipartFile file;

  public ReviewRequestDto(String content, int grade, MultipartFile file) {
    this.content = content;
    this.grade = grade;
    this.file = file;
  }

//  // 회원 ID
//  private int userId;

//  // 주문 ID
//  private String orderId;

  // ID 데이터타입 String to UUID
  public UUID getId() {
    if (id == null || id.isEmpty()) {
      return null;
    }
    try {
      return UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

}
