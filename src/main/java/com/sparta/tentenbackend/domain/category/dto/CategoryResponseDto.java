package com.sparta.tentenbackend.domain.category.dto;

import com.sparta.tentenbackend.domain.category.entity.Category;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {
  // 카테고리 ID
  private UUID id;
  // 카테고리 이름
  private String name;

  public CategoryResponseDto(Category category) {
    this.name = category.getName();
  }
}
