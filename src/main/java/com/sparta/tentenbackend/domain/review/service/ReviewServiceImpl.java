package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.review.repository.ReviewRepository;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final OrderRepository orderRepository;

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
  public List<ReviewResponseDto> findAllReviews() {
    // user.getId() == requestDto.getUserId() 확인
    // List<Reveiw> reviews = reviewRepository.findAllByUserIdAndIsDeletedFalse(user.getId());
    List<Review> reviews = reviewRepository.findAllByIsDeletedFalse();
    List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
    for (Review review : reviews) {
      reviewResponseDtoList.add(new ReviewResponseDto(review));
    }
    return reviewResponseDtoList;
  }

  // 리뷰 수정
  @Override
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
  public void removeReview(ReviewRequestDto requestDto) {
    // user.getId() == requestDto.getUserId() 확인
    Review review = reviewRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NotFoundException("해당 리뷰는 존재하지 않습니다."));
    review.markAsDeleted();
    reviewRepository.save(review);
  }


}
