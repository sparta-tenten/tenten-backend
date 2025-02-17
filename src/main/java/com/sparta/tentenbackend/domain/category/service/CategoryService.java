package com.sparta.tentenbackend.domain.category.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import java.util.List;

public interface CategoryService {
  // 카테고리 추가
  CategoryResponseDto addCategory(CategoryRequestDto requestDto);

  List<CategoryResponseDto> findAllCategories();

  CategoryResponseDto modifyCategory(CategoryRequestDto requestDto);

  void removeCategory(CategoryRequestDto requestDto);
}
