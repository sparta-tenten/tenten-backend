package com.sparta.tentenbackend.domain.review.entity;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.review.dto.ReviewRequestDto;
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

  public Review(ReviewRequestDto requestDto) {   // ,Order order
    this.content = requestDto.getContent();
    this.grade = requestDto.getGrade();
    this.image = requestDto.getImage();
//    this.order = order;
  }

  public void updateById(ReviewRequestDto requestDto) {
    this.content = requestDto.getContent();
    this.grade = requestDto.getGrade();
    this.image = requestDto.getImage();
  }

  public void markAsDeleted() {
    this.setDeleted(true);
    this.setDeletedAt(LocalDateTime.now());
  }
}
