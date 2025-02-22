package com.sparta.tentenbackend.domain.review.entity;

import com.sparta.tentenbackend.domain.review.dto.OwnerReviewRequestDto;
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
@Table(name = "p_owner_review")
@NoArgsConstructor
public class OwnerReview extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String content;

  @OneToOne
  @JoinColumn(name = "review_id", unique = true)
  private Review review;

  public OwnerReview(OwnerReviewRequestDto requestDto, Review review) {
    this.content = requestDto.getContent();
    this.review = review;
  }

  public void updateById(OwnerReviewRequestDto requestDto) {
    this.content = requestDto.getContent();
  }

  public void markAsDeleted() {
    this.setDeleted(true);
    this.setDeletedAt(LocalDateTime.now());
  }
}
