package com.sparta.tentenbackend.domain.review.controller;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.OwnerReviewResponseDto;
import com.sparta.tentenbackend.domain.review.service.OwnerReviewService;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    // 리뷰에 사장님 답글 달기
    @PostMapping
    public ResponseEntity<OwnerReviewResponseDto> createOwnerReview(
        @RequestBody OwnerReviewRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        OwnerReviewResponseDto response = ownerReviewService.addOwnerReview(requestDto,
            userDetails.getUser());
        return ResponseEntity.ok(response);
    }

  // 답글 수정
  @PutMapping
  public ResponseEntity<OwnerReviewResponseDto> updateOwnerReview(@RequestBody OwnerReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    OwnerReviewResponseDto response = ownerReviewService.modifyOwnerReview(requestDto, userDetails.getUser());
    return ResponseEntity.ok(response);
  }

  // 답글 삭제
  @DeleteMapping
  public ResponseEntity<String> deleteOwnerReview(@RequestParam String ownerReviewId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    ownerReviewService.removeOwnerReview(ownerReviewId, userDetails.getUser());
    return ResponseEntity.ok("답글 삭제 완료했습니다.");
  }

}
