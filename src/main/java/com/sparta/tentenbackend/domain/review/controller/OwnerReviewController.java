package com.sparta.tentenbackend.domain.review.controller;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.OwnerReviewResponseDto;
import com.sparta.tentenbackend.domain.review.service.OwnerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner/review")
@RequiredArgsConstructor
public class OwnerReviewController {
  private final OwnerReviewService ownerReviewService;
  // TODO 리뷰 생성, 수정, 삭제에 user 넣기
  // @AuthenticationPrincipal UserDetailsImpl userDetails

  // 리뷰 만들기
  @PostMapping
  public ResponseEntity<OwnerReviewResponseDto> createOwnerReview(@RequestBody OwnerReviewRequestDto requestDto) {
    OwnerReviewResponseDto response = ownerReviewService.addOwnerReview(requestDto);
    return ResponseEntity.ok(response);
  }

  // 리뷰 수정
  @PutMapping
  public ResponseEntity<OwnerReviewResponseDto> updateOwnerReview(@RequestBody OwnerReviewRequestDto requestDto) {
    OwnerReviewResponseDto response = ownerReviewService.modifyOwnerReview(requestDto);
    return ResponseEntity.ok(response);
  }

  // 리뷰 삭제
  @DeleteMapping
  public ResponseEntity<String> deleteOwnerReview(@RequestParam String ownerReviewId) {
    ownerReviewService.removeOwnerReview(ownerReviewId);
    return ResponseEntity.ok("답글 삭제를 완료했습니다.");
  }

}
