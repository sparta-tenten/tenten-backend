package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.review.repository.ReviewRepository;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final OrderRepository orderRepository;
  // TODO 리뷰 생성, 수정, 삭제 user 넣기

  // 리뷰 만들기
  @Override
  public ReviewResponseDto addReview(ReviewRequestDto requestDto) {
//    UUID orderId = UUID.fromString(requestDto.getOrderId());
//    // 주문내역 불러오기
//    Order order = orderRepository.findById(orderId).orElseThrow(() -> {
//      throw new NotFoundException("해당 주문을 찾을 수 없습니다.");
//    });
//    // 배달완료인 주문내역인 경우에만 리뷰 작성 가능
//    if (order.getOrderStatus() != OrderStatus.DELIVERY_COMPLETED) {
//      throw new BadRequestException("리뷰는 배달완료된 주문에 대해서만 작성할 수 있습니다.");
//    }
//    Review review = reviewRepository.save(new Review(requestDto, order));
    Review review = reviewRepository.save(new Review(requestDto));
    return new ReviewResponseDto(review);
  }

  // 리뷰 목록 조회
  @Override
  public Page<ReviewResponseDto> findAllReviews(int page, int size, String sortBy, boolean isAsc) {
    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    // user.getId() == requestDto.getUserId() 확인
    // Page<Review> reviews = reviewRepository.findAllByUserIdAndIsDeletedFalse(user.getId());
    Page<Review> reviews = reviewRepository.findAllByIsDeletedFalse(pageable);
    return reviews.map(ReviewResponseDto::new);
  }

  // 리뷰 수정
  @Override
  @Transactional
  public ReviewResponseDto modifyReview(ReviewRequestDto requestDto) {
    // user.getId() == requestDto.getUserId() 확인
    Review review = reviewRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NotFoundException("해당 리뷰는 존재하지 않습니다."));
    review.updateById(requestDto);
    reviewRepository.save(review);
    return new ReviewResponseDto(review);
  }

  // 리뷰 삭제
  @Override
  @Transactional
  public void removeReview(ReviewRequestDto requestDto) {
    // user.getId() == requestDto.getUserId() 확인
    Review review = reviewRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NotFoundException("해당 리뷰는 존재하지 않습니다."));
    review.markAsDeleted();
    reviewRepository.save(review);
  }

}
