package com.sparta.tentenbackend.domain.category.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  // 카테고리 추가
  @Override
  public CategoryResponseDto addCategory(CategoryRequestDto requestDto) {
//  public CategoryResponseDto addCategory(CategoryRequestDto requestDto, User user) {
    // user role = MASTER인 경우에만 카테고리 생성 가능 or Exception
    Category category = categoryRepository.save(new Category(requestDto));
//    Category category = categoryRepository.save(new Category(requestDto, user));
    return new CategoryResponseDto(category);
  }
}
