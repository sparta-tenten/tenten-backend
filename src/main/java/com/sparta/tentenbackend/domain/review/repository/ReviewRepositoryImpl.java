package com.sparta.tentenbackend.domain.review.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.tentenbackend.domain.menu.entity.QMenu;
import com.sparta.tentenbackend.domain.menu.entity.QMenuOrder;
import com.sparta.tentenbackend.domain.order.entity.QOrder;
import com.sparta.tentenbackend.domain.review.entity.QReview;
import com.sparta.tentenbackend.domain.review.entity.Review;
import com.sparta.tentenbackend.domain.store.entity.QStore;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
    super(Review.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<Review> findReviewsByStoreName(Long userId, String keyword, String sortBy, boolean isAsc, Pageable pageable) {
    QReview review = QReview.review;
    QOrder order = QOrder.order;
    QStore store = QStore.store;

    // 정렬 조건 적용
    OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sortBy, isAsc);

    JPAQuery<Review> query = queryFactory.selectFrom(review)
        .join(review.order, order)
        .join(order.store, store)
        .where(store.name.containsIgnoreCase(keyword)
            .and(order.user.id.eq(userId))
            .and(review.isDeleted.isFalse()))
        .orderBy(orderSpecifier);

    List<Review> reviews = getQuerydsl().applyPagination(pageable, query).fetch();
    long total = query.fetchCount();

    return new PageImpl<>(reviews, pageable, total);
  }

  @Override
  public Page<Review> findReviewsByMenuName(Long userId, String keyword, String sortBy, boolean isAsc, Pageable pageable) {
    QReview review = QReview.review;
    QOrder order = QOrder.order;
    QStore store = QStore.store;
    QMenuOrder menuOrder = QMenuOrder.menuOrder;
    QMenu menu = QMenu.menu;

    // 정렬 조건 적용
    OrderSpecifier<?> orderSpecifier = getOrderSpecifier(sortBy, isAsc);

    JPAQuery<Review> query = queryFactory.selectFrom(review)
        .join(review.order, order)
        .join(order.store, store)
        .join(order.menuOrderList, menuOrder)  // 메뉴 주문 목록과 연결
        .join(menuOrder.menu, menu)  // 메뉴와 연결
        .where(menu.name.containsIgnoreCase(keyword)  // 메뉴명으로 검색
            .and(order.user.id.eq(userId))  // 사용자 ID로 필터링
            .and(review.isDeleted.isFalse()))  // 삭제되지 않은 리뷰만 필터링
        .orderBy(orderSpecifier);

    List<Review> reviews = getQuerydsl().applyPagination(pageable, query).fetch();
    long total = query.fetchCount();

    return new PageImpl<>(reviews, pageable, total);
  }

  private OrderSpecifier<?> getOrderSpecifier(String sortBy, boolean isAsc) {
    QReview review = QReview.review;
    // 정렬 기준을 받아 정렬 설정
    if ("createdAt".equalsIgnoreCase(sortBy)) {
      return isAsc ? review.createdAt.asc() : review.createdAt.desc();  // 생성일 기준
    } else if ("updatedAt".equalsIgnoreCase(sortBy)) {
      return isAsc ? review.updatedAt.asc() : review.updatedAt.desc();  // 수정일 기준
    } else {
      // 기본적으로 생성일 기준으로 정렬
      return isAsc ? review.createdAt.asc() : review.createdAt.desc();
    }
  }

}

