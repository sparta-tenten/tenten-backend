package com.sparta.tentenbackend.domain.category.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
  // 카테고리 ID
  private UUID id;
  // 카테고리 이름
  private String name;
}
