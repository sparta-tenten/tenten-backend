package com.sparta.tentenbackend.domain.category.entity;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "p_category")
@NoArgsConstructor
public class Category extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

  public Category(CategoryRequestDto requestDto) {
    this.name = requestDto.getName();
  }

  public void updateById(CategoryRequestDto requestDto) {
    this.name = requestDto.getName();
  }

  public void markAsDeleted() { // isDeleted true
    this.setDeleted(true);
    this.setDeletedAt(LocalDateTime.now());
  }

}
