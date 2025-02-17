package com.sparta.tentenbackend.domain.category.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.category.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  // 카테고리 추가
  @Override
  public CategoryResponseDto addCategory(CategoryRequestDto requestDto) {
    Category category = categoryRepository.save(new Category(requestDto));
    return new CategoryResponseDto(category);
  }

  // 카테고리 목록 조회
  @Override
  public List<CategoryResponseDto> findAllCategories() {
    List<Category> categoryList = categoryRepository.findAllByIsDeletedFalse();
    List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
    for (Category category : categoryList) {
      categoryResponseDtoList.add(new CategoryResponseDto(category));
    }
    return categoryResponseDtoList;
  }

  // 카테고리 수정
  @Override
  public CategoryResponseDto modifyCategory(CategoryRequestDto requestDto) {
    Category category = categoryRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NullPointerException("해당 카테고리는 존재하지 않습니다."));
    category.updateById(requestDto);
    categoryRepository.save(category);
    return new CategoryResponseDto(category);
  }

  // 카테고리 삭제
  @Override
  public void removeCategory(CategoryRequestDto requestDto) {
    Category category = categoryRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NullPointerException("해당 카테고리는 존재하지 않습니다."));
    category.markAsDeleted(); // 카테고리의 isDeleted true
    categoryRepository.save(category);
  }

    /* user 구현 후 생성/수정/삭제
    // user role = MASTER인 경우에만 가능 or Exception 메서드 따로 빼기
   */

}
