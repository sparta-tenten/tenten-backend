package com.sparta.tentenbackend.domain.review.entity;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.review.dto.CreateReviewRequestDto;
import com.sparta.tentenbackend.domain.review.dto.UpdateReviewRequestDto;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "p_review")
@NoArgsConstructor
public class Review extends BaseEntity {

  // 리뷰 ID
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  // 리뷰 내용
  @Column(nullable = false)
  private String content;

  // 리뷰 평점 1~5
  @Column(nullable = false)
  private int grade;

  // 리뷰 이미지 URL
  @Column
  private String image;

  @OneToOne
  @JoinColumn(name = "order_id")
  private Order order;

  public Review(CreateReviewRequestDto requestDto, String imageUrl, Order order) {
    this.content = requestDto.getContent();
    this.grade = requestDto.getGrade();
    this.image = imageUrl;
    this.order = order;
  }

  public void updateById(UpdateReviewRequestDto requestDto, String imageUrl) {
    this.content = requestDto.getContent();
    this.grade = requestDto.getGrade();
    this.image = imageUrl;
  }

  public void markAsDeleted() {
    this.setDeleted(true);
    this.setDeletedAt(LocalDateTime.now());
  }

  public void reWriteReview(String content, int grade, String imageUrl) {
    this.content = content;
    this.grade = grade;
    this.image = imageUrl;
    this.setDeleted(false);
    this.setDeletedAt(null);
  }
}
