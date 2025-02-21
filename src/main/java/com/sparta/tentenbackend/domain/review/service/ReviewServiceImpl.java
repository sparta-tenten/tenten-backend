package com.sparta.tentenbackend.domain.review.service;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.entity.OrderStatus;
import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import com.sparta.tentenbackend.domain.review.dto.CreateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.ReviewResponseDto;
import com.sparta.tentenbackend.domain.review.dto.UpdateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.review.repository.ReviewRepository;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import com.sparta.tentenbackend.global.service.S3Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
//  private final StoreRepository storeRepository;
  private final S3Service s3Service;

  // TODO 주문 API 완성되면 주석 없얘고 테스트해보기
  // TODO 평점별 sorted 리뷰 목록 조회 만들기

  // 리뷰 만들기
  @Override
  @Transactional
  public ReviewResponseDto addReview(CreateReviewRequestDto requestDto, User user) throws IOException {
    UUID orderId = UUID.fromString(requestDto.getOrderId());
    // 주문내역 불러오기
    Order order = orderRepository.findById(orderId).orElseThrow(() -> {
      throw new NotFoundException("해당 주문내역을 찾을 수 없습니다.");
    });
//    // 로그인한 유저가 주문한 유저인지 확인
//    if (!user.getId().equals(order.getUser().getId())) {
//      throw new ForbiddenException("본인의 주문에 대해서만 리뷰를 작성할 수 있습니다.");
//    }
    // 배달완료인 주문내역인 경우에만 리뷰 작성 가능
    if (order.getOrderStatus() != OrderStatus.DELIVERY_COMPLETED) {
      throw new BadRequestException("리뷰는 배달완료된 주문에 대해서만 작성할 수 있습니다.");
    }
    // 파일이 존재하면 S3에 파일 업로드
    String imageUrl = null;
    if (requestDto.getFile() != null && !requestDto.getFile().isEmpty()) {
      imageUrl = s3Service.uploadFile(requestDto.getFile());
    }
    Review review = reviewRepository.save(new Review(requestDto, imageUrl, order));
    // 리뷰 생성시 가게 총 평점 합계, 총 리뷰 개수 저장
//    Store store = order.getStore();
//    store.updateReviewStats(requestDto.getGrade());
//    storeRepository.save(store);
    return new ReviewResponseDto(review);
  }

  // 리뷰 목록 조회
  @Override
  public Page<ReviewResponseDto> findAllReviews(User user, int page, int size, String sortBy, boolean isAsc) {
    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);

    // review 테이블에 저장된 order로 order.getUser().getId() == user.getId() 인 리뷰 목록/isDeleted=false만 조회하기
    Page<Review> reviews = reviewRepository.findAllByOrder_User_IdAndIsDeletedFalse(user.getId(), pageable);
//    Page<Review> reviews = reviewRepository.findAllByIsDeletedFalse(pageable);
    return reviews.map(ReviewResponseDto::new);
  }

  @Override
  public Page<ReviewResponseDto> searchReviewsByKeyword(User user, int searchType, String keyword, int page, int size, String sortBy, boolean isAsc) {
    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortBy);
    Pageable pageable = PageRequest.of(page, getValidPageSize(size), sort);

    Page<Review> reviews;
    if (searchType == 1) { // 가게명 검색
      reviews = reviewRepository.findReviewsByStoreName(user.getId(), keyword, pageable);
    } else if (searchType == 2) { // 메뉴명 검색
      reviews = reviewRepository.findReviewsByMenuName(user.getId(), keyword, pageable);
    } else {
      throw new IllegalArgumentException("searchType은 1(가게명) 또는 2(메뉴명)이어야 합니다.");
    }
    return reviews.map(ReviewResponseDto::new);
  }

  private int getValidPageSize(int size) {
    // 허용되는 페이지 크기 목록
    List<Integer> allowedSizes = Arrays.asList(10, 30, 50);
    // 페이지 크기가 허용된 값에 없으면 기본 10으로 설정
    if (!allowedSizes.contains(size)) {
      return 10;
    }
    return size;
  }

  // 리뷰 수정
  @Override
  @Transactional
  public ReviewResponseDto modifyReview(UpdateReviewRequestDto requestDto, User user) throws IOException {
    Review review = reviewRepository.findById(UUID.fromString(requestDto.getId())).orElseThrow(() ->
        new NotFoundException("해당 리뷰는 존재하지 않습니다."));
    // 리뷰 작성자 == 주문자 == 로그인 유저인지 확인하기
//    if (review.getOrder().getUser().getId() != user.getId()) {
//      throw new ForbiddenException("해당 리뷰의 작성자가 아닙니다.");
//    }
//    Store store = review.getOrder().getStore();
    int oldGrade = review.getGrade(); // 기존 평점
    // 파일이 존재하면 파일 대체
    String imageUrl = null;
    if (requestDto.getFile() != null && !requestDto.getFile().isEmpty()) {
      imageUrl = s3Service.updateFile(review.getImage(), requestDto.getFile());
    }
    // 리뷰 내용 업데이트
    review.updateById(requestDto, imageUrl);
    // 가게 총 평점 합계 업데이트
//    store.modifyReviewStats(oldGrade, requestDto.getGrade());
    reviewRepository.save(review);
//    storeRepository.save(store);
    return new ReviewResponseDto(review);
  }

  // 리뷰 삭제
  @Override
  @Transactional
  public void removeReview(String reviewId, User user) {
    // 리뷰 작성자 == 주문자 == 로그인 유저인지 확인하기
//    if (review.getOrder().getUser().getId() != user.getId()) {
//      throw new ForbiddenException("해당 리뷰의 작성자가 아닙니다.");
//    }
    Review review = reviewRepository.findById(UUID.fromString(reviewId)).orElseThrow(() ->
        new NotFoundException("해당 리뷰는 존재하지 않습니다."));
//    Store store = review.getOrder().getStore();
    s3Service.deleteFile(review.getImage());
    review.markAsDeleted();
//    store.removeReviewStats(review.getGrade());
    reviewRepository.save(review);
//    storeRepository.save(store);
  }
}

