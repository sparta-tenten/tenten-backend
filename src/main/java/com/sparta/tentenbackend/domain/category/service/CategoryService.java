package com.sparta.tentenbackend.domain.category.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;

public interface CategoryService {
  // 카테고리 추가
  CategoryResponseDto addCategory(CategoryRequestDto requestDto);
}
