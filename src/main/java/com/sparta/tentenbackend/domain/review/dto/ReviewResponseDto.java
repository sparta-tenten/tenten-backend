package com.sparta.tentenbackend.domain.review.dto;

import com.sparta.tentenbackend.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

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

    public ReviewResponseDto(Review review) {
        this.id = review.getId().toString();  // ValidUUID to String
        this.content = review.getContent();
        this.grade = review.getGrade();
        this.image = review.getImage();
//    this.orderId = review.getOrder().getId().toString();
    }
}
