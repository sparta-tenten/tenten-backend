package com.sparta.tentenbackend.domain.review.controller;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.review.service.ReviewService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;
  // TODO 리뷰 생성, 수정, 삭제에 user 넣기
  // @AuthenticationPrincipal UserDetailsImpl userDetails

  // 리뷰 만들기
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ReviewResponseDto> createReview(@RequestPart("review") ReviewRequestDto requestDto,
      @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
    if (file != null) {
      System.out.println("File Name : " + file.getOriginalFilename());
      requestDto = new ReviewRequestDto(requestDto.getContent(), requestDto.getGrade(), file);
    }
    ReviewResponseDto response = reviewService.addReview(requestDto);
    return ResponseEntity.ok(response);
  }

  // 리뷰 목록 조회
  @GetMapping
  public Page<ReviewResponseDto> getAllReviews(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortBy") String sortBy, @RequestParam("isAsc") boolean isAsc) {
    return reviewService.findAllReviews(page-1, size, sortBy, isAsc);
  }

  // 리뷰 수정
  @PutMapping
  public ReviewResponseDto updateReview(@RequestBody ReviewRequestDto requestDto) {
    return reviewService.modifyReview(requestDto);
  }

  // 리뷰 삭제
  @DeleteMapping
  public String deleteReview(@RequestParam String reviewId) {
    reviewService.removeReview(reviewId);
    return "리뷰 삭제를 완료했습니다.";
  }

}
