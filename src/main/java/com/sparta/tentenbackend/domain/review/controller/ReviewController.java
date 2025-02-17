package com.sparta.tentenbackend.domain.review.controller;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.review.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;

  // 리뷰 만들기
  @PostMapping("/review")
  public ReviewResponseDto createReview(@RequestBody ReviewRequestDto requestDto) {
    return reviewService.addReview(requestDto);
  }

  // 리뷰 목록 조회
  @GetMapping("/review")
  public List<ReviewResponseDto> getAllReviews() {
    return reviewService.findAllReviews();
  }

  // 리뷰 수정
  @PutMapping("/review")
  public ReviewResponseDto updateReview(@RequestBody ReviewRequestDto requestDto) {
    return reviewService.modifyReview(requestDto);
  }

  // 리뷰 삭제
  @DeleteMapping("/review")
  public String deleteReview(@RequestBody ReviewRequestDto requestDto) {
    reviewService.removeReview(requestDto);
    return "리뷰 삭제를 완료했습니다.";
  }

}
