package com.sparta.tentenbackend.domain.category.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.user.entity.User;
import java.util.List;

public interface CategoryService {
  // 카테고리 추가
  CategoryResponseDto addCategory(CategoryRequestDto requestDto, User user);

  // 카테고리 목록 조회
  List<CategoryResponseDto> findAllCategories();

  // 카테고리 수정
  CategoryResponseDto modifyCategory(CategoryRequestDto requestDto, User user);

  // 카테고리 삭제
  void removeCategory(String categoryId, User user);
}
