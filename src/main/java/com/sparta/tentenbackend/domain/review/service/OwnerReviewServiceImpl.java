package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.OwnerReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.OwnerReview;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.review.repository.OwnerReviewRepository;
import com.sparta.tentenbackend.domain.review.repository.ReviewRepository;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerReviewServiceImpl implements OwnerReviewService {
  private final OwnerReviewRepository ownerReviewRepository;
  private final ReviewRepository reviewRepository;

  // TODO user 생성, 수정, 삭제에 싹 넣기
  // 리뷰에 사장님 답글 달기
  @Override
  public OwnerReviewResponseDto addOwnerReview(OwnerReviewRequestDto requestDto) {
    UUID reviewId = UUID.fromString(requestDto.getReviewId());
    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new NotFoundException("해당 리뷰를 찾을 수 없습니다."));
    // 유저아이디가 가게 사장님 아이디와 일치할 때
//    if (user.getId().equals(review.getOrder().getStore().getId())) {
    OwnerReview ownerReview = ownerReviewRepository.save(new OwnerReview(requestDto, review));
    return new OwnerReviewResponseDto(ownerReview);
//    } else { throw new UnauthorizedException("해당 리뷰에 대한 권한이 없습니다."); }
  }

  // 답글 수정
  @Override
  public OwnerReviewResponseDto modifyOwnerReview(OwnerReviewRequestDto requestDto) {
    OwnerReview ownerReview = ownerReviewRepository.findById(requestDto.getId())
        .orElseThrow(() -> new NotFoundException("해당 답글을 찾을 수 없습니다."));
    // 유저아이디가 가게 사장님 아이디와 일치할 때
//    if (user.getId().equals(review.getOrder().getStore().getId())) {
    ownerReview.updateById(requestDto);
    ownerReviewRepository.save(ownerReview);
    return new OwnerReviewResponseDto(ownerReview);
//    } else { throw new UnauthorizedException("해당 리뷰에 대한 권한이 없습니다."); }
  }

  // 답글 삭제
  @Override
  public void removeOwnerReview(String ownerReviewId) {
    OwnerReview ownerReview = ownerReviewRepository.findById(UUID.fromString(ownerReviewId))
        .orElseThrow(() -> new NotFoundException("해당 답글을 찾을 수 없습니다."));
    // 유저아이디가 가게 사장님 아이디와 일치할 때
//    if (user.getId().equals(review.getOrder().getStore().getId())) {
    ownerReview.markAsDeleted();
    ownerReviewRepository.save(ownerReview);
//    } else { throw new UnauthorizedException("해당 리뷰에 대한 권한이 없습니다."); }
  }
}
