package com.sparta.tentenbackend.domain.review.controller;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.OwnerReviewResponseDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.OwnerReview;
import com.sparta.tentenbackend.domain.review.service.OwnerReviewService;
import com.sparta.tentenbackend.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerReviewController {
  private final OwnerReviewService ownerReviewService;
  // TODO 리뷰 생성, 수정, 삭제에 user 넣기
  // @AuthenticationPrincipal UserDetailsImpl userDetails

  // 리뷰 만들기
  @PostMapping("/review")
  public OwnerReviewResponseDto createOwnerReview(@RequestBody OwnerReviewRequestDto requestDto) {
    return ownerReviewService.addOwnerReview(requestDto);
  }

  // 리뷰 수정
  @PutMapping("/review")
  public OwnerReviewResponseDto updateOwnerReview(@RequestBody OwnerReviewRequestDto requestDto) {
    return ownerReviewService.modifyOwnerReview(requestDto);
  }

  // 리뷰 삭제
  @DeleteMapping("/review")
  public String deleteOwnerReview(@RequestParam String ownerReviewId) {
    ownerReviewService.removeOwnerReview(ownerReviewId);
    return "답글 삭제를 완료했습니다.";
  }

}
